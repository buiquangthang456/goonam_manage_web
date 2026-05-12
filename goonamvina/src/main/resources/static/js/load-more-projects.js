
        document.addEventListener('DOMContentLoaded', function() {
            var menuToggle = document.querySelector('.menu-toggle');
            var nav = document.querySelector('nav');

            if (menuToggle) {
                menuToggle.addEventListener('click', function() {
                    menuToggle.classList.toggle('active');
                    nav.classList.toggle('active');
                });
            }

            var loadMoreButton = document.getElementById('load-more-button-project');
            var productsList = document.getElementById('category-project');
            var products = productsList.getElementsByClassName('project-item');
            var itemsToShow = 4; // Số lượng sản phẩm muốn hiển thị ban đầu
            var itemsPerClick = 4; // Số lượng sản phẩm muốn hiển thị mỗi lần nhấn nút

            // Ẩn tất cả sản phẩm sau sản phẩm thứ 8
            for (var i = itemsToShow; i < products.length; i++) {
                products[i].classList.add('hidden');
            }

            loadMoreButton.addEventListener('click', function() {
                var currentlyDisplayed = 0;
                for (var i = 0; i < products.length; i++) {
                    if (!products[i].classList.contains('hidden')) {
                        currentlyDisplayed++;
                    }
                }

                for (var i = currentlyDisplayed; i < currentlyDisplayed + itemsPerClick; i++) {
                    if (products[i]) {
                        products[i].classList.remove('hidden');
                    }
                }

                if (currentlyDisplayed + itemsPerClick >= products.length) {
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
