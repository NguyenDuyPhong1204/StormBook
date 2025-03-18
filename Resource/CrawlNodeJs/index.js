const axios = require("axios");
const fs = require("fs");
const path = require("path");
const cheerio = require("cheerio");
const https = require('https');
// const cloudinary = require("cloudinary").v2;

// Cấu hình Cloudinary
// cloudinary.config({
//   cloud_name: "dgo9xrxhu",
//   api_key: "353827519624881",
//   api_secret: "6A6NOHRY5XFp6_iV0YpdYrgzATE",
// });

// Danh sách các chương cần tải ảnh
const nameTruyen = "thiet-huyet-kiem-si-hoi-quy";
const chapterNumbers = [1, 2, 3, 4, 5]; // Thay đổi theo số chương bạn muốn
const baseChapterUrl =
  "https://truyenqqto.com/truyen-tranh/ta-la-ta-de-8456-chap-";

const userHeaders = {
  "User-Agent":
    "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Mobile Safari/537.36",
  Referer: "https://truyenqqto.com",
  Accept: "image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8",
};

// Tạo thư mục cho truyện nếu chưa tồn tại
const outputDir = path.join(__dirname, nameTruyen);
if (!fs.existsSync(outputDir)) {
    fs.mkdirSync(outputDir, { recursive: true });
}

async function downloadImage(imageUrl, chapterNumber, id) {
  try {
    // Tạo thư mục cho chapter nếu chưa tồn tại
    const chapterDir = path.join(outputDir, `chapter_${chapterNumber}`);
    if (!fs.existsSync(chapterDir)) {
      fs.mkdirSync(chapterDir, { recursive: true });
    }

    console.log(
      `📥 Đang tải ảnh ${id} từ chương ${chapterNumber}: ${imageUrl}`
    );

    return new Promise((resolve, reject) => {
      const request = https.get(imageUrl, {
        headers: userHeaders
      }, (response) => {
        const contentType = response.headers['content-type'];
        let extension = 'jpg'; // mặc định
        if (contentType.includes('png')) extension = 'png';
        else if (contentType.includes('jpeg') || contentType.includes('jpg')) extension = 'jpg';
        else if (contentType.includes('webp')) extension = 'webp';

        const imagePath = path.join(chapterDir, `${id}.${extension}`);
        const fileStream = fs.createWriteStream(imagePath);

        response.pipe(fileStream);

        fileStream.on('finish', () => {
          console.log(`✔ Ảnh ${id} từ chương ${chapterNumber} đã tải về thành công!`);
          resolve({
            id,
            original_url: imageUrl,
            local_path: imagePath,
            status: "Success",
            download_time: new Date().toISOString(),
          });
        });

        fileStream.on('error', (err) => {
          reject(err);
        });
      });

      request.on('error', (err) => {
        reject(err);
      });
    });

  } catch (error) {
    console.error(
      `❌ Không thể tải ảnh ${id} từ chương ${chapterNumber}:`,
      error.message
    );
    return {
      id,
      original_url: imageUrl,
      local_path: null,
      status: "Failed",
    };
  }
}

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

    // Tải ảnh song song
    const downloadPromises = imageUrls.map((url, index) =>
      downloadImage(url, chapterNumber, index + 1)
    );
    const downloadedImages = await Promise.all(downloadPromises);

    const outputData = {
      title: $("title").text().trim(),
      chapterNumber,
      chapterUrl,
      totalImages: downloadedImages.length,
      images: downloadedImages,
    };

    // Lưu thông tin vào file JSON
    const outputPath = path.join(outputDir, `chapter_${chapterNumber}.json`);
    fs.writeFileSync(
      outputPath,
      JSON.stringify(outputData, null, 4),
      "utf-8"
    );
    console.log(
      `✅ Thông tin chương ${chapterNumber} đã được lưu vào ${outputPath}!`
    );
  } catch (error) {
    console.error(`❌ Lỗi khi tải ảnh từ chương ${chapterNumber}:`, error);
  }
}

// Lặp qua từng chương và tải ảnh
(async function fetchAllChapters() {
  for (const chapter of chapterNumbers) {
    const chapterUrl = `${baseChapterUrl}${chapter}.html`;
    await fetchImages(chapterUrl, chapter);
  }
})();
