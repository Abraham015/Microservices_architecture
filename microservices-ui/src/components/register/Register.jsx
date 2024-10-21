import React, {useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import Swal from "sweetalert2";

const Register = () => {
    const navigate=useNavigate();
    const [formData, setFormData] = useState({
        firstname: "",
        lastname: "",
        email: "",
        password: "",
        street: "",
        houseNumber: "",
        zipCode: ""
    });

    const handleChange = (e) => {
        const { id, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [id]: value
        }));
    };

    const registerCustomer = async () => {
        try {
            const response = await fetch("http://localhost:8222/api/v1/customers", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            });

            if (!response.ok) {
                await Swal.fire({
                    title: 'Error!',
                    text: 'Error',
                    icon: 'error',
                    confirmButtonText: 'Ok'
                });
            }

            Swal.fire({
                icon: "success",
                title: "User created successfully",
                confirmButtonText: 'Ok'
            }).then(()=>{
                navigate("/login");
            });
        } catch (error) {
            await Swal.fire({
                title: 'Error!',
                text: 'Error creating user',
                icon: 'error',
                confirmButtonText: 'Ok'
            });
        }
    };

    return (
    <div className="container-fluid card login-container p-4">
      <h3 className="text-center">Create an account</h3>
        <form className="row g-3" onSubmit={(e) => e.preventDefault()}>
            <div className="col-6">
                <label className="form-label">First Name</label>
                <input
                    type="email"
                    className="form-control"
                    id="firstname"
                    placeholder="John"
                    onChange={handleChange}
                />
            </div>
            <div className="col-6">
                <label className="form-label">Last Name</label>
                <input
                    type="email"
                    className="form-control"
                    id="lastname"
                    placeholder="John"
                    onChange={handleChange}
                />
            </div>
            <div className="col-6">
                <label className="form-label">Email address</label>
                <input
                    type="email"
                    className="form-control"
                    id="login"
                    placeholder="name@example.com"
                    onChange={handleChange}
                />
            </div>
            <div className="col-6">
                <label className="form-label">Password</label>
                <input
                    type="password"
                    className="form-control"
                    id="password"
                    placeholder="password"
                    onChange={handleChange}
                />
            </div>
            <div className="col-6">
                <label className="form-label">Street</label>
                <input
                    type="text"
                    className="form-control"
                    id="Street"
                    placeholder="Street"
                    onChange={handleChange}
                />
            </div>
            <div className="col-3">
                <label className="form-label">House number</label>
                <input
                    type="text"
                    className="form-control"
                    id="Number"
                    placeholder="House Number"
                    onChange={handleChange}
                />
            </div>
            <div className="col-3">
                <label className="form-label">Zip Code</label>
                <input
                    type="text"
                    className="form-control"
                    id="Number"
                    placeholder="Zip Code"
                    onChange={handleChange}
                />
            </div>

        </form>
        <div className="d-flex justify-content-between mb-3 p-3">
            <button type="button" className="btn btn-primary" onClick={registerCustomer}>
                <em className="fas fa-sign-in-alt"></em> &nbsp; Register
            </button>
            <div>
                Already have an account? &nbsp;
                <Link to="/login" className="btn btn-link"><em className="fas fa-sign-in-alt"></em> &nbsp; Login</Link>
        </div>
      </div>
    </div>
  );
};

export default Register;
