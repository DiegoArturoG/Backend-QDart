
const baseUrl = "https://qdart-qd0y.onrender.com";

const fetchData = async (url, options) => {
    try {
        const response = await fetch(url, options);
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
};

export const createItem = async (endpoint, item) => {
    const url = `${baseUrl}${endpoint}`;
    const options = {
        method: 'POST',
        body: JSON.stringify(item),
        headers: {
            'Content-Type': 'application/json'
        }
    };
    return fetchData(url, options);
};

export const readItems = async (endpoint) => {
    const url = `${baseUrl}${endpoint}`;
    return fetchData(url);
};

export const readItem = async (endpoint, id) => {
    const url = `${baseUrl}${endpoint}/${id}`;
    return fetchData(url);
};

export const updateItem = async (endpoint, item) => {
    const url = `${baseUrl}${endpoint}/${item.id}`;
    const options = {
        method: 'PUT',
        body: JSON.stringify(item),
        headers: {
            'Content-Type': 'application/json'
        }
    };
    return fetchData(url, options);
};

export const deleteItem = async (endpoint, id) => {
    const url = `${baseUrl}${endpoint}/${id}`;
    const options = {
        method: 'DELETE'
    };
    return fetchData(url, options);
};