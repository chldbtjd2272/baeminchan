class SideDishDetail {
  constructor() {
    this.loadProductDetail();
  }

  setHTMLWithData(){
    $('.desc_product_name').textContent = this.title;
    $('.top_detail_image').src = this.imgUrl;
    $('.desc_bt_txt').textContent = this.description;
    $('.sale-price').textContent = this.price;
    $('#detail_total_price').textContent = this.price;
    this.insertBreadCrumb();
  }

  

  setAllProductData({id,imgUrl,price,title,description,categoryRootTitle}){
    this.id = id;
    this.imgUrl =imgUrl;
    this.price = price;
    this.title = title;
    this.description = description;
    this.categoryRootTitle = categoryRootTitle;
    this.setHTMLWithData();
    }

  initializeTitles(response) {
    response.json().then(body => {
     this.setAllProductData(body);
    });
  }

  loadProductDetail() {
    fetchManager({
      url: "/api" + window.location.pathname,
      method: "GET",
      headers: { "content-type": "application/json" },
      callback: this.initializeTitles.bind(this)
    });
  }

  generateBreadCrumb(title) {
      let html =`<li><a href="/">${title}</a></li>`
      return html;
  }

  insertBreadCrumb(){
    let html ='';
    this.categoryRootTitle.forEach(title => {
        html += this.generateBreadCrumb(title) 
    });
    console.log(html);
    
    $('#breadcrumb').insertAdjacentHTML('beforeend',html);
  }
}




document.addEventListener("DOMContentLoaded", () => {
     sideDishDetail = new SideDishDetail();
  });
  