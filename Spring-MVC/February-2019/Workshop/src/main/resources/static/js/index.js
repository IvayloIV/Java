$(() => {
    const url = '/product/api/category/';
    const home = $('#home');
    const productList = home.find('#productsList');
    home.find('input[name=category]').on('change', changeCategory);

    async function changeCategory(event) {
        let categoryId = $(event.target).val();
        let dataJson = await fetch(url + categoryId, { method: 'GET' });
        let data = await dataJson.json();

        productList.empty();
        productList.append($('<h1>').addClass('text-center font-weight-bold').text('Products'))
            .append($('<div>').addClass('products-data')
                .append($('<div>').addClass('products-row row d-flex justify-content-around mt-5')));

        if (data.length === 0) {
            productList.find('.products-data > div')
                .append($('<h4>').text('Category is empty.'))
        } else {
            data.forEach(p => productList.find('.products-data > div')
                .append($('<div>').addClass('product')
                    .append($('<div>').addClass('text-center')
                        .append($('<a>').attr('href', '/product/details/' + p.id)
                            .append($('<img>').attr('src', p.imageUrl).attr('alt', 'Image not loaded...').addClass('product-image-home img-thumbnail px-auto'))))
                    .append($('<h5>').addClass('text-center font-weight-bold mt-3').text('Name: ' + p.name))
                    .append($('<h5>').addClass('text-center font-weight-bold').text('Price: ' + p.price))));
        }
    }
});