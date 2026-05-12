document.addEventListener('DOMContentLoaded', function() {
    var menuItems = document.querySelectorAll('.sidebar ul li > a');

    menuItems.forEach(function(item) {
        item.addEventListener('click', function(event) {
            var submenu = this.nextElementSibling;
            if (submenu && submenu.tagName === 'UL') {
                event.preventDefault();  // Ngăn chặn hành động mặc định của thẻ a
                submenu.style.display = (submenu.style.display === 'block') ? 'none' : 'block';
            }
        });
    });
});
document.getElementById('toggle-btn').addEventListener('click', function() {
    var sidebar = document.getElementById('sidebar');
    if (sidebar.style.display === 'none' || sidebar.style.display === '') {
        sidebar.style.display = 'block';
    } else {
        sidebar.style.display = 'none';
    }
});