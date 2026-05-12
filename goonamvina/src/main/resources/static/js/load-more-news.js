  document.addEventListener('DOMContentLoaded', function() {
            var menuToggle = document.querySelector('.menu-toggle');
            var nav = document.querySelector('nav');

            if (menuToggle) {
                menuToggle.addEventListener('click', function() {
                    menuToggle.classList.toggle('active');
                    nav.classList.toggle('active');
                });
            }

            var loadMoreButton = document.getElementById('load-more-button-news');
            var newsList = document.getElementById('category-news');
            var newsItems = newsList.children;
            var itemsToShow = 4; // Số lượng tin tức muốn hiển thị ban đầu
            var itemsPerClick = 4; // Số lượng tin tức muốn hiển thị mỗi lần nhấn nút

            // Ẩn tất cả tin tức sau tin tức thứ 8
            for (var i = itemsToShow; i < newsItems.length; i++) {
                newsItems[i].classList.add('hidden');
            }

            loadMoreButton.addEventListener('click', function() {
                var hiddenItems = newsList.querySelectorAll('.news-item.hidden');
                for (var i = 0; i < itemsPerClick && i < hiddenItems.length; i++) {
                    hiddenItems[i].classList.remove('hidden');
                }

                if (newsList.querySelectorAll('.news-item.hidden').length === 0) {
                    loadMoreButton.style.display = 'none';
                }
            });
        });

        window.onscroll = function() {
            var header = document.querySelector("header");
            if (window.pageYOffset > 50) {
                header.classList.add("small-header");
            } else {
                header.classList.remove("small-header");
            }
        };