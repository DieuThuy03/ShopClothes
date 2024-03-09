export function formatNgaySinh(dateString) {
    if (!dateString) {
        return "...";
    }

    const date = new Date(dateString);

    if (isNaN(date.getTime())) {
        return "...";
    }

    const day = date.getDate().toString().padStart(2, "0"); // Đảm bảo rằng ngày và tháng có đủ 2 chữ số
    const month = (date.getMonth() + 1).toString().padStart(2, "0"); // Đảm bảo rằng ngày và tháng có đủ 2 chữ số
    const year = date.getFullYear();

    return `${day}/${month}/${year}`;

}

export function formatNgayTao(dateString) {
    if (!dateString) {
        return "...";
    }

    const date = new Date(dateString);

    if (isNaN(date.getTime())) {
        return "...";
    }

    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, "0"); // Đảm bảo rằng ngày và tháng có đủ 2 chữ số
    const day = date.getDate().toString().padStart(2, "0"); // Đảm bảo rằng ngày và tháng có đủ 2 chữ số
    const hours = date.getHours().toString().padStart(2, "0"); // Đảm bảo rằng giờ có đủ 2 chữ số
    const minutes = date.getMinutes().toString().padStart(2, "0"); // Đảm bảo rằng phút có đủ 2 chữ số
    const seconds = date.getSeconds().toString().padStart(2, "0"); // Đảm bảo rằng giây có đủ 2 chữ số

    return `${day}/${month}/${year} ${hours}:${minutes}:${seconds}`; // Trả về chuỗi định dạng "dd/MM/yyyy HH:mm:ss"
}