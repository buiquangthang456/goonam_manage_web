 document.addEventListener('DOMContentLoaded', function () {


            var swiper = new Swiper('.swiper-container', {
                slidesPerView: 1,
                spaceBetween: 10,
                loop: true,
                pagination: {
                    el: '.swiper-pagination',
                    clickable: true,
                },
                navigation: {
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev',
                },
                autoplay: {
                    delay: 5000,
                    disableOnInteraction: false,
                },
                breakpoints: {
                    640: {
                        slidesPerView: 1,
                        spaceBetween: 20,
                    },
                    768: {
                        slidesPerView: 2,
                        spaceBetween: 40,
                    },
                    1024: {
                        slidesPerView: 3,
                        spaceBetween: 50,
                    },
                }
            });
        });

        window.onscroll = function() {
            var header = document.querySelector("header");
            if (window.pageYOffset > 50) { // Khi cuộn xuống hơn 50px
                header.classList.add("small-header");
            } else {
                header.classList.remove("small-header");
            }
        };
        let slideIndex = 0;

        function showSlides() {
            let slides = document.getElementsByClassName("slide");
            for (let i = 0; i < slides.length; i++) {
                slides[i].classList.remove("active-slide");
            }
            slideIndex++;
            if (slideIndex > slides.length) { slideIndex = 1; }
            slides[slideIndex - 1].classList.add("active-slide");
            setTimeout(showSlides, 3000); // Change image every 3 seconds
        }

        document.addEventListener("DOMContentLoaded", showSlides);


        document.addEventListener("DOMContentLoaded", function() {
            const columns = document.querySelectorAll('.project-column');
            const transitionTime = 5000; // Thay đổi mỗi 5 giây

            columns.forEach(column => {
                const items = column.querySelectorAll('.project-item');
                const leftArrow = column.querySelector('.arrow-left');
                const rightArrow = column.querySelector('.arrow-right');
                let index = 0;

                function showProject(index) {
                    items.forEach((item, i) => {
                        item.style.display = 'none';
                    });

                    items[index].style.display = 'block';
                }

                function showNextProject() {
                    index = (index + 1) % items.length;
                    showProject(index);
                }

                function showPreviousProject() {
                    index = (index - 1 + items.length) % items.length;
                    showProject(index);
                }

                showProject(index); // Hiển thị dự án đầu tiên ngay lập tức
                setInterval(showNextProject, transitionTime); // Tự động chuyển đổi dự án

                leftArrow.addEventListener('click', showPreviousProject);
                rightArrow.addEventListener('click', showNextProject);
            });
        });

  window.addEventListener('scroll', function() {
          const header = document.querySelector('header');
          if (window.scrollY > 50) {
              header.classList.add('header-scrolled');
          } else {
              header.classList.remove('header-scrolled');
          }
      });
       document.getElementById('menu-toggle').addEventListener('click', function() {
                     var mainNav = document.querySelector('.main-nav');
                     mainNav.classList.toggle('open');
                 });
          document.addEventListener("DOMContentLoaded", function () {
              const toggles = document.querySelectorAll('.submenu-toggle');

              toggles.forEach(toggle => {
                  toggle.addEventListener('click', function (event) {
                      event.preventDefault(); // Ngăn hành động mặc định
                      event.stopPropagation(); // Ngăn sự kiện lan truyền

                      const submenu = this.nextElementSibling; // Lấy menu con tiếp theo
                      submenu.classList.toggle('open'); // Thêm/xóa class 'open' để hiển thị/ẩn menu con

                      // Xoay mũi tên khi mở/đóng menu
                      this.classList.toggle('rotate');
                  });
              });
          });