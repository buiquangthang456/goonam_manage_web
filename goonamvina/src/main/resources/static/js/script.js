            window.onscroll = function() {
                var header = document.querySelector("header");
                if (window.pageYOffset > 50) { // Khi cuộn xuống hơn 50px
                    header.classList.add("small-header");
                } else {
                    header.classList.remove("small-header");
                }
            };

            window.addEventListener('load', function () {
              expandParent('.jsExpand');
              nav();
            });
            function expandParent(className) {
              var expandEls = document.querySelectorAll(className);

              if (expandEls.length > 0) {
                expandEls.forEach(function (expandEl) {
                  expandEl.addEventListener('click', function (event) {
                    if (expandEl.parentElement.classList.contains('expand')) {
                      expandEl.parentElement.classList.remove('expand');
                    } else {
                      expandEl.parentElement.classList.add('expand');
                    }

                    event.preventDefault();
                  });
                });
              }
            }
            function nav() {
              var navEl = document.getElementById('nav-mobile'),
                  navBtnEl = document.getElementById('menu-toggle');
            //      navBtnCloseEl = document.getElementById('menu-toggle');

              if (navBtnEl) {
                navBtnEl.addEventListener('click', function () {
                  if (navEl.classList.contains('show')) {
                    navEl.classList.remove('show');
                  } else {
                    navEl.classList.add('show');
                  }
                });
              }

            //  if (navBtnCloseEl) {
            //    navBtnCloseEl.addEventListener('click', function () {
            //      navEl.classList.remove('show');
            //    });
            //  }
            }



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


           // Slideshow 2 (Dự Án Tiêu Biểu)
           document.addEventListener("DOMContentLoaded", function() {
               const columns = document.querySelectorAll('.project-column');

               columns.forEach(column => {
                   const items = column.querySelectorAll('.project-item');
                   const leftArrow = column.querySelector('.arrow-left');
                   const rightArrow = column.querySelector('.arrow-right');
                   let index = 0;

                   function showProject(index) {
                       items.forEach(item => {
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
                   setInterval(showNextProject, 5000); // Tự động chuyển đổi dự án mỗi 5 giây

                   leftArrow.addEventListener('click', showPreviousProject);
                   rightArrow.addEventListener('click', showNextProject);
               });
           });
//           document.getElementById('menu-toggle').addEventListener('click', function() {
//               var mainNav = document.querySelector('.main-nav');
//               mainNav.classList.toggle('open');
//           });


      window.addEventListener('scroll', function() {
          const header = document.querySelector('header');
          if (window.scrollY > 50) {
              header.classList.add('header-scrolled');
          } else {
              header.classList.remove('header-scrolled');
          }
      });
//document.addEventListener("DOMContentLoaded", function () {
//    const toggles = document.querySelectorAll('.menu-mobile');
//
//    toggles.forEach(toggle => {
//        toggle.addEventListener('click', function (event) {
//            event.preventDefault();
//            event.stopPropagation();
//
//            const submenu = this.nextElementSibling;
//            const isSubmenuOpen = submenu.classList.remove('open');
//
////            if (isSubmenuOpen ) {
////                submenu.classList.remove('open');
////                this.classList.remove('rotate');
////            } else {
////                toggles.forEach(otherToggle => {
////                    const otherSubmenu = otherToggle.nextElementSibling;
////                    otherSubmenu.classList.remove('open');
////                    otherToggle.classList.remove('rotate');
////                });
////
////                submenu.classList.add('open');
////
////                this.classList.add('rotate');
////            }
//        });
//    });
//});

// Slideshow 1 (Banner chính)
let slideIndex1 = 1;
showSlides1(slideIndex1);

function plusSlides1(n) {
    showSlides1(slideIndex1 += n);
}

function showSlides1(n) {
    let i;
    let slides = document.getElementsByClassName("slide1"); // Đảm bảo chỉ tác động đến slide1
    if (slides.length === 0) return;
    if (n > slides.length) { slideIndex1 = 1 }
    if (n < 1) { slideIndex1 = slides.length }
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slides[slideIndex1 - 1].style.display = "block";
}

setInterval(function() {
    plusSlides1(1);
}, 3000); // Tự động chuyển đổi mỗi 3 giây


