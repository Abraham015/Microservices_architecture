import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

const Login = () => {

  const navigate = useNavigate();
  const [credentials, setCredentials] = useState({
    email: "",
    password: ""
  });

  const handleChange = (e) => {
    const { id, value } = e.target;
    setCredentials((prev) => ({
      ...prev,
      [id]: value
    }));
  };

  const handleLogin = async () => {
    try {
      const response = await fetch("http://localhost:8222/api/v1/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(credentials)
      });

      if (!response.ok) {
        throw new Error("Error en la autenticación");
      }

      const data = await response.json();
      console.log("Usuario autenticado:", data);

      // Mostrar mensaje de éxito
      Swal.fire({
        icon: "success",
        title: "Login successful",
        confirmButtonText: 'Ok'
      }).then(() => {
        navigate("/products");
      });
    } catch (error) {
      console.error("Error al iniciar sesión:", error);
      Swal.fire({
        icon: "error",
        title: "Error",
        text: "Email/password incorrect. Try again!"
      });
    }
  };

  return (
      <div className="container-fluid card login-container p-5">
        <div className="form-login">
          <div className="text-center">
            <h2>Login</h2>
          </div>
          <form className="mt-5" onSubmit={(e) => e.preventDefault()}>
            <div className="mb-4 col-lg-4 mx-auto">
              <label htmlFor="email" className="form-label fw-semibold">
                Email Address:
              </label>
              <input
                  type="email"
                  className="form-control"
                  id="email"
                  aria-describedby="emailHelp"
                  placeholder="Your email address"
                  onChange={handleChange}
              />
            </div>

            <div className="mb-4 col-lg-4 mx-auto">
              <label htmlFor="password" className="form-label fw-semibold">
                Password:
              </label>
              <input
                  type="password"
                  className="form-control"
                  id="password"
                  placeholder="Your password"
                  onChange={handleChange}
              />
            </div>
            <div className="d-grid col-lg-4 mx-auto">
              <button type="button" className="btn btn-primary btn-lg" onClick={handleLogin}>
                Sign In
              </button>
              <div>
                Don't have an account? &nbsp;
                <Link to="/register" className="btn btn-link"> <em className="fas fa-sign-in-alt"></em> &nbsp; Register</Link>
              </div>
            </div>
          </form>
        </div>
      </div>
  );
};

export default Login;