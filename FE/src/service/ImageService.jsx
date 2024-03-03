import HttpClient from '~/utils/http-client';

const API_URL = '/Image';

const ImageService = {

    getAll: (pageNo, pageSize, name, status) => {
        return HttpClient.get(`${API_URL}/getAll`, {
            params: { pageNo, pageSize, name, status }
        })
            .then(response => response)  // Sửa dòng này
            .catch(error => {
                console.error('Error in getAll:', error);
                throw error;
            });
    },

    // getAll: (pageNo, pageSize, name, status) => {
    //     return HttpClient.get(`${API_URL}getAll`, {
    //         params: { pageNo, pageSize, name, status }
    //     })
    //         .then(response => response.data)
    //         .catch(error => {
    //             console.error('Error in getAll:', error);
    //             throw error;
    //         });
    // },

    create: (data) => {
        return HttpClient.post(`${API_URL}/create`, data)
            .then(response => response.data)
            .catch(error => {
                console.error('Error in create:', error);
                throw error;
            });
    },

    // update: (id, data) => {
    //     return HttpClient.put(`${API_URL}update?id=${id}`, data)
    //         .then(response => response.data)
    //         .catch(error => {
    //             console.error('Error in update:', error);
    //             throw error;
    //         });
    // },

    delete: (id) => {
        return HttpClient.delete(`${API_URL}delete?id=${id}`)
            .then(response => response.data)
            .catch(error => {
                console.error('Error in delete:', error);
                throw error;
            });
    },

    findImageByProductId: (productId) => {
        return HttpClient.get(`${API_URL}/findImageByProductId?productId=${productId}`)
            .then(response => response)
            .catch(error => {
                console.error('Error in findImageByProductId:', error);
                throw error;
            });
    },
};

export default ImageService;