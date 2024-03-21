import HttpClient from '~/utils/http-client';

const API_URL = '/public/product';

const ProductService = {
    getAll: (pageNo, pageSize, name, status) => {
        return HttpClient.get(`${API_URL}/display`, {
            params: { pageNo, pageSize, name, status }
        })
            .then(response => response)
            .catch(error => {
                console.error('Error in getAll:', error);
                throw error;
            });
    },
    getAll1: (pageNo, pageSize, name, status) => {
        return HttpClient.get(`${API_URL}/display2`, {
            params: { pageNo, pageSize, name, status }
        })
            .then(response => response)
            .catch(error => {
                console.error('Error in getAll:', error);
                throw error;
            });
    },
    findProductsByFilters: (data) => {
        return HttpClient.post(`${API_URL}findProductsByFilters`, data)
            .then(response => response.data)
            .catch(error => {
                console.error('Error in getAll:', error);
                throw error;
            });
    },

    findNamePriceByProductId: (productId) => {
        return HttpClient.get(`${API_URL}/filterProductByNamePrice?idProduct=${productId}`, {
            params: {
                productId
            }
        }).then(response => response.data)
            .catch(error => {
                console.error('Error in getAll:', error);
                throw error;
            });
    },
};

export default ProductService;