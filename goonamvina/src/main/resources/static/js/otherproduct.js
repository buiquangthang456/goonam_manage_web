document.addEventListener('DOMContentLoaded', function () {
    var subProductCategoryName = document.body.dataset.subcategoryName || '';
    var otherProductsSection = document.getElementById('category-other');

    if (subProductCategoryName === 'Cửa thép/Cửa thép chống cháy') {
        if (otherProductsSection) {
            otherProductsSection.style.display = 'none';
        }
    }
});
