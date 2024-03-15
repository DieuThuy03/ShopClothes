import HttpClient from '~/utils/http-client';

const API_URL = '/Vocher';

const VoucherService = {

    getAll: (pageNo, pageSize, name, status) => {
        return HttpClient.get(`${API_URL}/hien-thi-page`, {
            params: { pageNo, pageSize, name, status }
        })
            .then(response => response)  // Sửa dòng này
            .catch(error => {
                console.error('Error in getAll:', error);
                throw error;
            });
    },
    // getVoucherByFilter: (data) => {
    //     return HttpClient.post(`${API_URL}getVoucherByFilter`, data)
    //         .then(response => response.data)
    //         .catch(error => {
    //             console.error('Error in getAll:', error);
    //             throw error;
    //         });
    // },

    create: (data) => {
        return HttpClient.post(`${API_URL}/add`, data)
            .then(response => response.data)
            .catch(error => {
                console.error('Error in create:', error);
                console.log(data);
                throw error;

            });
    },

    update: (id, data) => {
        // return HttpClient.put(`${API_URL}/update/${id}`, data)
        return HttpClient.put(`${API_URL}/update/${id}`, data)
            .catch(error => {
                console.log('URL API:', `${API_URL}/update/${id}`);
                console.error('Error in update:', error);
                throw error;
            });
    },

    delete: (id) => {
        if (!isNaN(id)) {
            const url = `${API_URL}/delete/${id}`;
            console.log("DELETE request URL:", url);
            return HttpClient.delete(url)
                .then(response => response.data)
                .catch(error => {
                    console.error('Error in delete:', error);
                    if (error.response) {
                        console.error('Server responded with:', error.response.data);
                    }
                    throw error;
                });
        } else {
            console.error('ID không hợp lệ:', id);
        }
    },
    updateStatus: (id) => {

        if (!isNaN(id)) {
            const url = `${API_URL}/cancel-voucher/${id}`;
            console.log("UpdateStatus request URL:", url);
            return HttpClient.put(url)
                .then(response => response.data)
                .catch(error => {
                    console.error('Error in delete:', error);
                    if (error.response) {
                        console.error('Server responded with:', error.response.data);
                    }
                    throw error;
                });
        } else {
            console.error('ID không hợp lệ:', id);
        }
    },
    findVoucherByID: (id) => {
        return HttpClient.get(`${API_URL}/${id}`)
            .then(response => response)
            .catch(error => {
                console.error('Error in findVoucherByID:', error);
                throw error;
            });
    },

    findVoucherByCode: (code) => {
        return HttpClient.get(`${API_URL}/${code}`)
            .then(response => response)
            .catch(error => {
                console.error('Error in findVoucherByCode:', error);
                throw error;
            });
    },
};

export default VoucherService;