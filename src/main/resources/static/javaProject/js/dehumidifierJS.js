$(document).ready(() => {
  var products = [
    // 您的商品數據
  ];
  const fetchProducts = () => {
    // $.get('https://fakestoreapi.com/products/',result);
    $.get('http://localhost:8080/api/in/v1/products/categories/dehumidifier', result);

  };

  const result = (data) => {
    products = data;
    displayProducts();
    //generatePagination();
  };

  // const productsPerPage = 4; currentPage
  // let currentPage = 1; (page)

  function displayProducts() {
    //   const startIndex = (page - 1) * productsPerPage;
    //   const endIndex = startIndex + productsPerPage;    let i= startIndex; i < endIndex &&

    $('#productsContainer').empty();

    for (let i = 0; i < products.length; i++) {
      const product = products[i];
      console.log(product.sku)
      $('#productsContainer').append(`
        <li class="col-md-4 col-6 mb-3">
        <div class="card product-item">
          <div class="card-thumb">
            <a class="card-img" href="#">
              <img src="/javaProject/images/dehumidifier/${product.sku}/0.jpg" alt="">
              <img class="card-img-hover" src="" alt="">
            </a>
            <div class="card-tag">
              <span class="badge badge-primary px-2">-18%</span>
            </div>
            <div class="card-vertical">
              <button class="btn"><i class="far fa-heart"></i></button>
            </div>
          </div>
          <div class="card-body text-center">
            <a href="#" class="h6 card-title">${product.name}</a>
            <p class="card-text">$${product.price}</p>
          </div>
          <div class="card-footer d-flex justify-content-between">
            <a class="add-to-cart btn btn-block" data-name="${product.name}" data-price="$${product.price}">加入購物車</a>
          </div>
        </div>
      </li>
        `);
    }
  }

  // function generatePagination() {
  //   $('#productPagination').empty();

  //   const totalPages = Math.ceil(products.length / productsPerPage);

  //   for (let i = 1; i <= totalPages; i++) {
  //     const pageItem = $(`
  //       <li class="page-item ${i === currentPage ? 'active' : ''}">
  //         <a class="page-link" href="#" data-page="${i}">${i}</a>
  //       </li>
  //     `);

  //     pageItem.on('click', (e) => {
  //       e.preventDefault();
  //       currentPage = parseInt($(e.target).data('page'));
  //       displayProducts(currentPage);
  //       generatePagination();
  //     });

  //     $('#productPagination').append(pageItem);
  //   }
  // }

  fetchProducts();


});



