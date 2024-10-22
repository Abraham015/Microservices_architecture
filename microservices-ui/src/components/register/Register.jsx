import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

const Register = () => {
    const navigate = useNavigate();
    const [customer, setCustomer] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        address: {
            street: '',
            houseNumber: '',
            zipCode: ''
        }
    });

    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name.includes('.')) {
            const [key, subKey] = name.split('.');
            setCustomer(prev => ({
                ...prev,
                [key]: {
                    ...prev[key],
                    [subKey]: value
                }
            }));
        } else {
            setCustomer(prev => ({
                ...prev,
                [name]: value
            }));
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setError(null);

        try {
            console.log(JSON.stringify(customer)); // Verifica el objeto customer
            const response = await fetch('http://localhost:8222/api/v1/customers/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(customer),
            });

            const responseText = await response.text(); // Leer el cuerpo como texto

            let data;
            try {
                data = JSON.parse(responseText); // Intentar parsear el texto como JSON
            } catch (error) {
                data = responseText; // Si no es JSON, usar el texto
            }

            if (!response.ok) {
                throw new Error(data);
            }

            Swal.fire({
                title: 'Success!',
                text: 'User successfully created',
                icon: 'success',
                confirmButtonText: 'Ok'
            });

            navigate("/login");
        } catch (error) {
            setError(error.message);
            Swal.fire({
                title: 'Error!',
                text: error.message,
                icon: 'error',
                confirmButtonText: 'Try Again'
            });
            console.error('Error:', error);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="container-fluid card login-container p-4">
            <h3 className="text-center">Create an account</h3>
            <form className="row g-3" onSubmit={handleSubmit}>
                <div className="col-6">
                    <label className="form-label">First Name</label>
                    <input
                        type="text"
                        className="form-control"
                        name="firstName" // Cambiado a name
                        placeholder="John"
                        onChange={handleChange}
                    />
                </div>
                <div className="col-6">
                    <label className="form-label">Last Name</label>
                    <input
                        type="text"
                        className="form-control"
                        name="lastName" // Cambiado a name
                        placeholder="Doe"
                        onChange={handleChange}
                    />
                </div>
                <div className="col-6">
                    <label className="form-label">Email address</label>
                    <input
                        type="email"
                        className="form-control"
                        name="email" // Cambiado a name
                        placeholder="name@example.com"
                        onChange={handleChange}
                    />
                </div>
                <div className="col-6">
                    <label className="form -label">Password</label>
                    <input
                        type="password"
                        className="form-control"
                        name="password" // Cambiado a name
                        placeholder="password"
                        onChange={handleChange}
                    />
                </div>
                <div className="col-6">
                    <label className="form-label">Street</label>
                    <input
                        type="text"
                        className="form-control"
                        name="address.street" // Cambiado a name
                        placeholder="Street"
                        onChange={handleChange}
                    />
                </div>
                <div className="col-3">
                    <label className="form-label">House number</label>
                    <input
                        type="text"
                        className="form-control"
                        name="address.houseNumber" // Cambiado a name
                        placeholder="House Number"
                        onChange={handleChange}
                    />
                </div>
                <div className="col-3">
                    <label className="form-label">Zip Code</label>
                    <input
                        type="text"
                        className="form-control"
                        name="address.zipCode" // Cambiado a name
                        placeholder="Zip Code"
                        onChange={handleChange}
                    />
                </div>
                <button type="submit" className="btn btn-primary">
                    <em className="fas fa-sign-in-alt"></em> &nbsp; Register
                </button>
            </form>

            <div className="d-flex justify-content-between mb-3 p-3">
                <div>
                    Already have an account? &nbsp;
                    <Link to="/login" className="btn btn-link"><em
                        className="fas fa-sign-in-alt"></em> &nbsp; Login</Link>
                </div>
            </div>
        </div>
    );
};

export default Register;