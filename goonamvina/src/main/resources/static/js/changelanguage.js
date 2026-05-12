
    // Hàm để lấy giá trị của cookie theo tên
    function getCookie(name) {
        let value = "; " + document.cookie;
        let parts = value.split("; " + name + "=");
        if (parts.length === 2) return parts.pop().split(";").shift();
    }

    document.addEventListener('DOMContentLoaded', function () {
        const languageSwitch = document.getElementById('language-switch');
        const currentLanguage = getCookie('lang');  // Lấy giá trị ngôn ngữ từ cookie

        // Kiểm tra giá trị của cookie 'lang' để thiết lập trạng thái nút
        if (currentLanguage === 'en') {
            languageSwitch.checked = true;  // Nếu ngôn ngữ hiện tại là tiếng Anh, đánh dấu checkbox
        } else {
            languageSwitch.checked = false; // Nếu ngôn ngữ là tiếng Việt, bỏ đánh dấu checkbox
        }

        // Lắng nghe sự kiện thay đổi ngôn ngữ khi người dùng chuyển đổi
        languageSwitch.addEventListener('change', function() {
            if (this.checked) {
                window.location.href = '/change-language?lang=en'; // Chuyển sang tiếng Anh
            } else {
                window.location.href = '/change-language?lang=vi'; // Chuyển sang tiếng Việt
            }
        });
    });
     document.addEventListener('DOMContentLoaded', function () {
        const languageSwitch = document.getElementById('language-switch1');
        const currentLanguage = getCookie('lang');  // Lấy giá trị ngôn ngữ từ cookie

        // Kiểm tra giá trị của cookie 'lang' để thiết lập trạng thái nút
        if (currentLanguage === 'en') {
            languageSwitch.checked = true;  // Nếu ngôn ngữ hiện tại là tiếng Anh, đánh dấu checkbox
        } else {
            languageSwitch.checked = false; // Nếu ngôn ngữ là tiếng Việt, bỏ đánh dấu checkbox
        }

        // Lắng nghe sự kiện thay đổi ngôn ngữ khi người dùng chuyển đổi
        languageSwitch.addEventListener('change', function() {
            if (this.checked) {
                window.location.href = '/change-language?lang=en'; // Chuyển sang tiếng Anh
            } else {
                window.location.href = '/change-language?lang=vi'; // Chuyển sang tiếng Việt
            }
        });
    });
