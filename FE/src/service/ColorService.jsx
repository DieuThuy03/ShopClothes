import HttpClient from '~/utils/http-client';

const API_URL = '/colors';

const ColorService = {
    getAll: (pageNo) => {
        return HttpClient.get(`${API_URL}/hien-thi`, {
            params: { pageNo }
        })
            .then(response => response)
            .catch(error => {
                console.error('Error in getAll:', error);
                throw error;
            });
    },

    create: (data) => {
        return HttpClient.post(`${API_URL}/add`, data)
            .then(response => response.data)
            .catch(error => {
                console.error('Error in create:', error);
                throw error;
            });
    },

    update: (id, data) => {
        return HttpClient.put(`${API_URL}/update/${id}`, data)
            .then(response => response.data)
            .catch(error => {
                console.error('Error in update:', error);
                throw error;
            });
    },

    delete: (id) => {
        return HttpClient.delete(`${API_URL}/delete/${id}`)
            .then(response => response.data)
            .catch(error => {
                console.error('Error in delete:', error);
                throw error;
            });
    },
    findAllByDeletedTrue: () => {
        return HttpClient.get(`${API_URL}findAllByDeletedTrue`)
            .then(response => response)
            .catch(error => {
                console.error('Error in findAllByDeletedTrue:', error);
                throw error;
            });
    },
};

export default ColorService;