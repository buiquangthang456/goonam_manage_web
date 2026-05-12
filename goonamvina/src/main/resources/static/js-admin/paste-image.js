var quillVi = new Quill('#editorVi', {
    theme: 'snow',
    modules: {
      toolbar: [
        [{ 'header': [1, 2, false] }],
        ['bold', 'italic', 'underline'],
        ['image', 'code-block']
      ],
      clipboard: {
        matchVisual: false
      }
    }
  });

  quillVi.on('text-change', function() {
    document.getElementById('contentVi').value = quillVi.root.innerHTML;
  });

  var quillEn = new Quill('#editorEn', {
     theme: 'snow',
     modules: {
       toolbar: [
         [{ 'header': [1, 2, false] }],
         ['bold', 'italic', 'underline'],
         ['image', 'code-block']
       ],
       clipboard: {
         matchVisual: false
       }
     }
   });

   quillEn.on('text-change', function() {
     document.getElementById('contentEn').value = quillEn.root.innerHTML;
   });