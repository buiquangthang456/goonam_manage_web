(function () {
    document.addEventListener('DOMContentLoaded', function () {
        var items = Array.prototype.slice.call(document.querySelectorAll('.pd-gallery-item'));
        if (!items.length) return;

        var lb = document.getElementById('pd-lightbox');
        var lbImg = document.getElementById('pd-lightbox-img');
        var btnClose = document.getElementById('pd-lightbox-close');
        var btnPrev = document.getElementById('pd-lightbox-prev');
        var btnNext = document.getElementById('pd-lightbox-next');
        if (!lb || !lbImg) return;

        var idx = 0;

        function show(i) {
            if (i < 0) i = items.length - 1;
            if (i >= items.length) i = 0;
            idx = i;
            var img = items[i].querySelector('img');
            if (!img) return;
            lbImg.src = img.getAttribute('data-full') || img.src;
            lbImg.alt = img.alt || '';
            lb.classList.add('show');
            document.body.style.overflow = 'hidden';
        }

        function close() {
            lb.classList.remove('show');
            document.body.style.overflow = '';
        }

        items.forEach(function (it, i) {
            it.addEventListener('click', function () { show(i); });
        });
        if (btnClose) btnClose.addEventListener('click', close);
        if (btnPrev) btnPrev.addEventListener('click', function (e) { e.stopPropagation(); show(idx - 1); });
        if (btnNext) btnNext.addEventListener('click', function (e) { e.stopPropagation(); show(idx + 1); });
        if (lb) lb.addEventListener('click', function (e) {
            if (e.target === lb) close();
        });
        document.addEventListener('keydown', function (e) {
            if (!lb.classList.contains('show')) return;
            if (e.key === 'Escape') close();
            else if (e.key === 'ArrowLeft') show(idx - 1);
            else if (e.key === 'ArrowRight') show(idx + 1);
        });
    });
})();
