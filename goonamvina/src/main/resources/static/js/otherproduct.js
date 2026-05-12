 document.addEventListener('DOMContentLoaded', function () {
        var subProductCategoryName = /* th:text="${subProductCategory.name}" */;
        var otherProductsSection = document.getElementById('category-other');

        if (subProductCategoryName === 'Cửa thép/Cửa thép chống cháy') {
            if (otherProductsSection) {
                otherProductsSection.style.display = 'none';
            }
        }
    });