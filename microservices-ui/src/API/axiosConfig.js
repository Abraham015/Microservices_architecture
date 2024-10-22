import axios from 'axios';

export default axios.create({
    baseURL:'http://localhost:8222/',
    headers: {
        'Content-Type': 'application/json',
    },
});