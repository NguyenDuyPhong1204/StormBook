const axios = require("axios");
const fs = require("fs");
const cloudinary = require("cloudinary").v2;
const cheerio = require("cheerio");

// Cấu hình Cloudinary
cloudinary.config({
  cloud_name: "dgo9xrxhu",
  api_key: "353827519624881",
  api_secret: "6A6NOHRY5XFp6_iV0YpdYrgzATE",
});

// Danh sách các chương cần tải ảnh
const nameTruyen = "thiet-huyet-kiem-si-hoi-quy";
const chapterNumbers = [1, 2, 3, 4, 5]; // Thay đổi theo số chương bạn muốn
const baseChapterUrl =
  "https://truyenqqto.com/truyen-tranh/thiet-huyet-kiem-si-hoi-quy-14046-chap-";

const userHeaders = {
  "User-Agent":
    "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Mobile Safari/537.36",
  Referer: "https://truyenqqto.com",
  Accept: "image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8",
};

async function fetchImages(chapterUrl, chapterNumber) {
  try {
    const { data } = await axios.get(chapterUrl, { headers: userHeaders });
    const $ = cheerio.load(data);
    const base_url = "https://truyenqqto.com";

    let imageUrls = $("img")
      .map((i, el) => {
        let src = $(el).attr("src");
        return src.startsWith("http") ? src : base_url + src;
      })
      .get();

    console.log(
      `📖 Chương ${chapterNumber}: 🔍 Tìm thấy ${imageUrls.length} ảnh`
    );

    // Tải lên Cloudinary song song
    const uploadPromises = imageUrls.map((url, index) =>
      uploadToCloudinary(url, chapterNumber, index + 1)
    );
    const uploadedImages = await Promise.all(uploadPromises);

    const outputData = {
      title: $("title").text().trim(),
      chapterNumber,
      chapterUrl,
      totalImages: uploadedImages.length,
      images: uploadedImages,
    };

    // Lưu dữ liệu vào file JSON riêng cho từng chương
    fs.writeFileSync(
      `${nameTruyen}_chapter_${chapterNumber}.json`,
      JSON.stringify(outputData, null, 4),
      "utf-8"
    );
    console.log(
      `✅ Ảnh từ chương ${chapterNumber} đã được lưu vào chapter_${chapterNumber}.json!`
    );
  } catch (error) {
    console.error(`❌ Lỗi khi tải ảnh từ chương ${chapterNumber}:`, error);
  }
}

// Hàm tải ảnh lên Cloudinary
async function uploadToCloudinary(imageUrl, chapterNumber, id) {
  try {
    console.log(
      `📤 Đang tải ảnh ${id} từ chương ${chapterNumber}: ${imageUrl}`
    );
    const response = await cloudinary.uploader.upload(imageUrl);
    console.log(
      `✔ Ảnh ${id} từ chương ${chapterNumber} đã tải lên thành công!`
    );

    return {
      id,
      original_url: imageUrl,
      upload_url: response.secure_url,
      status: "Success",
      upload_time: new Date().toISOString(),
    };
  } catch (error) {
    console.error(
      `❌ Không thể tải ảnh ${id} từ chương ${chapterNumber}:`,
      error
    );
    return {
      id,
      original_url: imageUrl,
      upload_url: "Failed",
      status: "Failed",
    };
  }
}

// Lặp qua từng chương và tải ảnh
(async function fetchAllChapters() {
  for (const chapter of chapterNumbers) {
    const chapterUrl = `${baseChapterUrl}${chapter}.html`;
    await fetchImages(chapterUrl, chapter);
  }
})();
