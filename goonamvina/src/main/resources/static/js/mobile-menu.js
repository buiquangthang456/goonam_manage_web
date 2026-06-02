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
      navBtnEl = document.getElementById('menu-toggle'),
      overlayEl = document.getElementById('menu-overlay');

  function openMenu() {
    if (navEl) navEl.classList.add('show');
    if (navBtnEl) navBtnEl.classList.add('is-open');
    if (overlayEl) overlayEl.classList.add('active');
    document.body.style.overflow = 'hidden';
  }
  function closeMenu() {
    if (navEl) navEl.classList.remove('show');
    if (navBtnEl) navBtnEl.classList.remove('is-open');
    if (overlayEl) overlayEl.classList.remove('active');
    document.body.style.overflow = '';
  }

  if (navBtnEl) {
    navBtnEl.addEventListener('click', function () {
      if (navEl && navEl.classList.contains('show')) {
        closeMenu();
      } else {
        openMenu();
      }
    });
  }
  if (overlayEl) {
    overlayEl.addEventListener('click', closeMenu);
  }
  document.addEventListener('keydown', function (e) {
    if (e.key === 'Escape') closeMenu();
  });
}