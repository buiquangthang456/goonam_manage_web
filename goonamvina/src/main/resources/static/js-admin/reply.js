document.querySelectorAll('.reply-button').forEach(button => {
    button.addEventListener('click', function() {
        const email = this.closest('tr').querySelector('td:nth-child(2)').innerText;
        document.getElementById('replyEmail').value = email;
        document.getElementById('replyModal').style.display = 'block';
    });
});

// Close modal
document.querySelector('.close').addEventListener('click', function() {
    document.getElementById('replyModal').style.display = 'none';
});

document.querySelectorAll('.reply-button').forEach(button => {
    button.addEventListener('click', function() {
        const email = this.closest('tr').querySelector('td:nth-child(2)').innerText;
        document.getElementById('replyEmail').value = email;
        document.getElementById('replyModal').style.display = 'block';
    });
});