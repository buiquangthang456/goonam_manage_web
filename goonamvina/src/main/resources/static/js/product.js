

let currentPage = 1;
const colorsPerPage = 28;
const totalColors = 112;
const colors = [
    { image: '/uploads/bang1/red.jpeg', code: 'PE2222GL, T/P Bordeaux Red' },
    { image: '/uploads/bang1/yellow.jpeg', code: 'PE4444GL, T/P D/Yellow' },
    { image: '/uploads/bang1/modifywhite.jpeg', code: 'PE0001GL, Modify White, EUW' },
    { image: '/uploads/bang1/slidingwhite.jpeg', code: 'PE0003GL, Sliding White' },
    { image: '/uploads/bang1/creamwhite.jpeg', code: 'PE0009GL, Cream White, RAL9001' },
    { image: '/uploads/bang1/graywhite.jpeg', code: 'PE0008GL, Gray White, RAL9002' },
    { image: '/uploads/bang1/lpwhite.jpeg', code: 'PE0015GL, LP White, RAL9003' },
    { image: '/uploads/bang1/purewhite.jpeg', code: 'PE0011GL, Pure White, RAL9010' },
    { image: '/uploads/bang1/trafficwhite.jpeg', code: 'PE0016LT, L/T Traffic White, RAL9016' },
    { image: '/uploads/bang1/smdpwhite.jpeg', code: 'MX0034LS, SM DP White' },
    { image: '/uploads/bang1/hvwhite.jpeg', code: 'PE0085GL, HV White' },
    { image: '/uploads/bang1/bluewhite.jpeg', code: 'MX0125GL, Blue White' },
    { image: '/uploads/bang1/violetwhite.jpeg', code: 'MX0138GL, Violet White'},
    { image: '/uploads/bang1/txwhite.jpeg', code: 'PE0133TX, T/X White' },
     { image: '/uploads/bang1/smblack.jpeg', code: 'PE1001LS, SM Black,RAL9004 '},
    { image: '/uploads/bang1/txblack.jpeg', code: 'PE1002TX, T/X Black '},
     { image: '/uploads/bang1/mattfurnitureblack.jpeg', code: 'MX1003MA, Matt Furniture Black '},
    { image: '/uploads/bang1/mattflatblack.jpeg', code: 'MX1006MA, Matt Flat Black '},
    { image: '/uploads/bang1/superblack.jpeg', code: 'PE1007GL, Super Black '},
    { image: '/uploads/bang1/smjtblack.jpeg', code: 'PE1021LS, SM JT Black, RAL9005 '},
    { image: '/uploads/bang1/sgbsblack.jpeg', code: 'MX1024SG, SG BS Black'},
    { image: '/uploads/bang1/mattttblack.jpeg', code: 'MX1023MA, Matt TT Black'},
    { image: '/uploads/bang1/bsblack.jpeg', code: 'MX1026GL, BS Black'},
    { image: '/uploads/bang1/txbsblack.jpeg', code: 'PE1029TX, T/X BS Black'},
    { image: '/uploads/bang1/lthmblack.jpeg', code: 'PE1030LT, L/T HM Black'},
    { image: '/uploads/bang1/electricalred.jpeg', code: 'PE2002GL, Electrical Red'},
    { image: '/uploads/bang1/ltsignalred.jpeg', code: 'PE2003LT, L/T Signal Red, RAL3001'},
    { image: '/uploads/bang1/ttpink.jpeg', code: 'MX2010GL, TT Pink'},

    { image: '/uploads/bang2/htpred.jpeg', code: 'MX2023GL, HTP RED'},
    { image: '/uploads/bang2/ltpnred.jpeg', code: 'MX2033LT, L/T PN RED'},
    { image: '/uploads/bang2/ntorange.jpeg', code: 'MX3004GL, NT Orange'},
    { image: '/uploads/bang2/rackingorange.jpeg', code: 'PE3001GL, Racking Orange'},
    { image: '/uploads/bang2/eelcolor.jpeg', code: 'PE3005GL, Eel Color'},
    { image: '/uploads/bang2/ltorange.jpeg', code: 'PE3027GL, L/T Orange'},
    { image: '/uploads/bang2/yellowwood.jpeg', code: 'PE4002GL, Yellow Wood'},
    { image: '/uploads/bang2/signalyellow.jpeg', code: 'PE4008GL, Signal Yellow, RAL1003'},
    { image: '/uploads/bang2/trafficyellow.jpeg', code: 'PE4009GL, Traffic Yellow, RAL1023'},
    { image: '/uploads/bang2/rackingblue.jpeg', code: 'PE5001GL, Racking Blue'},
    { image: '/uploads/bang2/seablue.jpeg', code: 'PE5002GL, Sea Blue'},
    { image: '/uploads/bang2/navyblue.jpeg', code: 'PE5003GL, Navy Blue'},
    { image: '/uploads/bang2/hcblue.jpeg', code: 'MX5004GL, HC Blue'},
    { image: '/uploads/bang2/ttviolet.jpeg', code: 'MX5015GL, TT Violet'},
    { image: '/uploads/bang2/ttblue.jpeg', code: 'MX5020GL, TT Blue,RAL5002'},
    { image: '/uploads/bang2/ltschoolblue.jpeg', code: 'MX5023LT, L/T School Blue'},
    { image: '/uploads/bang2/ndblue.jpeg', code: 'MX5110GL, ND Blue'},
    { image: '/uploads/bang2/nkblue.jpeg', code: 'PE5203GL, NK Blue'},
    { image: '/uploads/bang2/lightgreen.jpeg', code: 'PE6001GL, Light Green, RAL6018'},
    { image: '/uploads/bang2/lttealgreen.jpeg', code: 'PE6003LT, L/T Teal Green'},
    { image: '/uploads/bang2/forestgreen.jpeg', code: 'PE6006GL, Forest Green, RAL6029'},
    { image: '/uploads/bang2/pickelgreen.jpeg', code: 'PE6008GL, Pickel Green, 5G7/3.5'},
    { image: '/uploads/bang2/ltgreenmtd.jpeg', code: 'PE6009LT, L/T Green-MTD'},
    { image: '/uploads/bang2/tealcolor.jpeg', code: 'PE6010GL, Teal Color, RAL5021'},
    { image: '/uploads/bang2/mossgreen.jpeg', code: 'PE6030GL, Moss Green, RAL6005'},
    { image: '/uploads/bang2/dm7green.jpeg', code: 'MX6170GL, DM7 Green, Light RAL6005'},
    { image: '/uploads/bang2/dcgreen.jpeg', code: 'MX6171GL, DC Green'},
    { image: '/uploads/bang2/papgreen.jpeg', code: 'MX6215GL, Pap Green, RAL5021'},


{ image: '/uploads/bang3/txdarkbrown.jpeg', code: 'PE7001TX, T/X Dark Brown, 1002830'},
{ image: '/uploads/bang3/txsandcolor.jpeg', code: 'PE7003TX, T/X Sand Color'},
{ image: '/uploads/bang3/woodbrown.jpeg', code: 'PE7004GL, Wood Brown'},
{ image: '/uploads/bang3/cabinetcream.jpeg', code: 'PE7006GL, Cabinet Cream, RAL1013'},
{ image: '/uploads/bang3/txdarkcream.jpeg', code: 'PE7007TX, T/X Dark Cream'},
{ image: '/uploads/bang3/yellowcream.jpeg', code: 'PE7010GL, Yellow Cream'},
{ image: '/uploads/bang3/darkbrown.jpeg', code: 'PE7016GL, Dark Brown, RAL8017'},
{ image: '/uploads/bang3/cream.jpeg', code: 'PE7019GL, Cream, RAL1015'},
{ image: '/uploads/bang3/ltcream.jpeg', code: 'PE7020LT, L/T Cream, Light RAL1015'},
{ image: '/uploads/bang3/ltlbrownmtd.jpeg', code: 'PE7028LT, L/T/L Brown-MTD'},
{ image: '/uploads/bang3/ltlsandcolormtd.jpeg', code: 'PE7029LT, L/T/L Sand Color-MTD'},
{ image: '/uploads/bang3/ltbeigemtd.jpeg', code: 'PE7030LT, L/T Beige-MTD'},
{ image: '/uploads/bang3/browncream.jpeg', code: 'PE7033GL, Brown Cream'},
{ image: '/uploads/bang3/txtpbrown.jpeg', code: 'PE7192LT, T/X TP Brown'},
{ image: '/uploads/bang3/chcream.jpeg', code: 'MX7221GL, CH Cream'},
{ image: '/uploads/bang3/milkcoffeecolor.jpeg', code: 'MX7693GL, Milk Coffee Color'},
{ image: '/uploads/bang3/mpsilvergray.jpeg', code: 'MX8001GL, MP Silver Gray'},
{ image: '/uploads/bang3/deepmetallicyellow.jpeg', code: 'PE8006SG, Deep Metallic Yellow'},
{ image: '/uploads/bang3/htmgsgray.jpeg', code: 'PE8007HT, H/T MGS Gray'},
{ image: '/uploads/bang3/metallicyellow.jpeg', code: 'PE8008LS, Metallic Yellow'},
{ image: '/uploads/bang3/champagnehd02.jpeg', code: 'PE8011SG, Champagne-HD02'},
{ image: '/uploads/bang3/galaxyblack.jpeg', code: 'PE8019MA, Galaxy Black'},
{ image: '/uploads/bang3/brilliantgold.jpeg', code: 'MX8020GL, Brilliant Gold'},
{ image: '/uploads/bang3/ltblacksilver.jpeg', code: 'MX8182LT, L/T Black Silver'},
{ image: '/uploads/bang3/ltblackbronze.jpeg', code: 'MX8184LT, L/T Black Bronze'},
{ image: '/uploads/bang3/ltgoldencopper.jpeg', code: 'PE8192LT, L/T Golden Coppper'},
{ image: '/uploads/bang3/ltvlblacksilver.jpeg', code: 'PE8390LT, L/T VL Black Silver'},
{ image: '/uploads/bang3/whitealuminium.jpeg', code: 'PE8966GL, White Aluminium, RAL9006'},


{ image: '/uploads/bang4/txanchorgray.jpeg', code: 'PE9001TX, T/X Anchor Gray, XF Guangdong'},
{ image: '/uploads/bang4/ltroughbeige.jpeg', code: 'PE9002LT, L/T Rough Beige, RAL7032'},
{ image: '/uploads/bang4/txshutterdarkgray.jpeg', code: 'PE9008TX, T/X Shutter Dark Gray, XF VN'},
{ image: '/uploads/bang4/ltshuttercream.jpeg', code: 'PE9009SW, L/T Shutter Cream'},
{ image: '/uploads/bang4/ltshutterbeige.jpeg', code: 'PE9010LT, L/T Shutter Beige,RAL7032'},
{ image: '/uploads/bang4/ltmediumbeige.jpeg', code: 'PE9012LT, L/T Medium Beige, Light RAL7044'},
{ image: '/uploads/bang4/ltroughdarkbeige.jpeg', code: 'PE9013LT, L/T Rough Dark Beige, Dark RAL7032'},
{ image: '/uploads/bang4/ltroughcream.jpeg', code: 'PE9014LT, L/T Rough Cream'},
{ image: '/uploads/bang4/ltcloudygray.jpeg', code: 'PE9015LT, L/T Cloudy Gray, Light RAL7035'},
{ image: '/uploads/bang4/ltfroggray.jpeg', code: 'PE9016LT, L/T Frog Gray, RAL7035'},
{ image: '/uploads/bang4/flatgray.jpeg', code: 'PE9017GL, Flat Gray, Light RAL7035'},
{ image: '/uploads/bang4/cabinetgray.jpeg', code: 'PE9018GL, Cabinet Gray, 1022600-RAL7032'},
{ image: '/uploads/bang4/blackgray.jpeg', code: 'PE9019GL, Black Gray, RAL7024'},
{ image: '/uploads/bang4/sggray.jpeg', code: 'PE9021SG, SG Gray, HP05'},
{ image: '/uploads/bang4/slategray.jpeg', code: 'PE9022GL, Slate Gray, Light RAL7001'},
{ image: '/uploads/bang4/trolleylightgray.jpeg', code: 'PE9023GL, Trolley Light Gray, RAL7047'},
{ image: '/uploads/bang4/ltcabinetgray.jpeg', code: 'PE9027GLT, L/T Cabinet Gray'},
{ image: '/uploads/bang4/ivorygray.jpeg', code: 'PE9035GL, Ivory Gray'},
{ image: '/uploads/bang4/ltnpngray.jpeg', code: 'PE9043LT, L/T NPN Gray'},
{ image: '/uploads/bang4/lttarpaulingray.jpeg', code: 'PE9047LT, L/T Tarpaulin Gray, RAL7010'},
{ image: '/uploads/bang4/smnbdgray.jpeg', code: 'PE9051LS, SM NBD Gray'},
{ image: '/uploads/bang4/ltpapgray.jpeg', code: 'MX9076GSW, L/T PAP Gray, Light RAL7044'},
{ image: '/uploads/bang4/ltddtbeige.jpeg', code: 'MX9079LT, L/T DDT Beige, RAL7032'},
{ image: '/uploads/bang4/lthcgray.jpeg', code: 'MX9081LT, L/T HC Gray, RAL7035'},
{ image: '/uploads/bang4/papgray.jpeg', code: 'MX9082GL, PAP Gray'},
{ image: '/uploads/bang4/txxfmtd.jpeg', code: 'PE9178TX, T/X XF MTD'},
{ image: '/uploads/bang4/sgnkgray.jpeg', code: 'PE9643SG, SG NK Gray'},
{ image: '/uploads/bang4/sghp05gray.jpeg', code: 'MX9696SG, SG HP05 Gray'},

    // ... và tiếp tục thêm các màu khác cho đủ 112 màu
];

function toggleColorPicker() {
    const overlay = document.querySelector('.overlay');
    const colorPickerContainer = document.querySelector('.color-picker-container');

    if (!overlay || !colorPickerContainer) {
        console.error("Overlay or color-picker-container not found in DOM.");
        return;
    }

    overlay.classList.toggle('hidden');
    colorPickerContainer.classList.toggle('hidden');

    // Render colors when opening the picker
    if (!colorPickerContainer.classList.contains('hidden')) {
        const colorPickerBody = colorPickerContainer.querySelector('.color-picker-body');
        renderColors(colorPickerBody);
    }
}

let selectedColors = [];

function renderColors(container) {
    container.innerHTML = '';

    const start = (currentPage - 1) * colorsPerPage;
    const end = Math.min(start + colorsPerPage, totalColors);

    for (let i = start; i < end; i++) {
        const colorBox = document.createElement('div');
        colorBox.className = 'color-box';

        const img = document.createElement('img');
        img.src = colors[i].image;
        img.alt = colors[i].code;
        colorBox.appendChild(img);

        const codeText = document.createElement('p');
        codeText.textContent = colors[i].code;
        colorBox.appendChild(codeText);

        // Thêm checkbox để tích chọn màu
        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.className = 'color-checkbox';
        checkbox.setAttribute('data-code', colors[i].code);  // Thêm thuộc tính data-code
        checkbox.addEventListener('change', function() {
            if (this.checked) {
                selectColor(colors[i].code);
            } else {
                deselectColor(colors[i].code);
            }
        });
        colorBox.appendChild(checkbox);

        container.appendChild(colorBox);
    }
    // Vô hiệu hóa nút "Trang sau" nếu đang ở trang cuối cùng
    const nextButton = document.querySelector('.page-button[onclick="nextPage()"]');
    if (end >= totalColors) {
        nextButton.disabled = true;
    } else {
        nextButton.disabled = false;
    }
}


function previousPage() {
    if (currentPage > 1) {
        currentPage--;
        renderColors(document.querySelector('.color-picker-body'));
    }
}

function nextPage() {
    if (currentPage < Math.ceil(totalColors / colorsPerPage)) {
        currentPage++;
        renderColors(document.querySelector('.color-picker-body'));
    }
}

function selectColor(color) {
    if (!selectedColors.includes(color)) {
        selectedColors.push(color);
        updateSelectedColors();
    }
}
function deselectColor(color) {
    // Xóa màu khỏi danh sách đã chọn
    selectedColors = selectedColors.filter(c => c !== color);

    // Cập nhật hiển thị các mã màu đã chọn
    updateSelectedColors();

    // Tắt ô tích xanh
    const checkboxes = document.querySelectorAll('.color-checkbox');
    checkboxes.forEach(checkbox => {
        if (checkbox.getAttribute('data-code') === color) {
            checkbox.checked = false;
        }
    });
}

function updateSelectedColors() {
    const selectedColorsContainer = document.getElementById('selected-colors');
    selectedColorsContainer.innerHTML = '';

    selectedColors.forEach(color => {
        const colorTag = document.createElement('span');
        colorTag.className = 'color-tag';
        colorTag.textContent = color;

        const removeButton = document.createElement('button');
        removeButton.textContent = '×';
        removeButton.className = 'remove-color-button';
        removeButton.addEventListener('click', function() {
            deselectColor(color);
        });

        colorTag.appendChild(removeButton);
        selectedColorsContainer.appendChild(colorTag);
    });
}

