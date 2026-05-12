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