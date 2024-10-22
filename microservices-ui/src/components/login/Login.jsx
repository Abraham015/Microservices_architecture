import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

const Login = () => {
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
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

  const handleLogin = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    // Validar que las credenciales no estén vacías
    if (credentials.email === '' || credentials.password === '') {
      Swal.fire({
        title: 'Error!',
        text: 'Email/Password is missing',
        icon: 'error',
        confirmButtonText: 'Try Again'
      });
      setLoading(false);
      return; // Detener la ejecución si hay un error
    }

    try {
      console.log(JSON.stringify(credentials)); // Verifica el objeto credentials
      const response = await fetch('http://localhost:8222/api/v1/customers/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(credentials),
      });

      const responseText = await response.text(); // Leer el cuerpo como texto

      let data;
      try {
        data = JSON.parse(responseText); // Intentar parsear el texto como JSON
      } catch (error) {
        data = responseText; // Si no es JSON, usar el texto
      }

      if (!response.ok) {
        throw new Error(data); // Lanzar error si la respuesta no es OK
      }

      Swal.fire({
        title: 'Success!',
        text: 'Login success',
        icon: 'success',
        confirmButtonText: 'Ok'
      });

      navigate("/");
    } catch (error) {
      setError(error.message);
      Swal.fire({
        title: 'Error!',
        text: 'Wrong credentials', // Mensaje más claro
        icon: 'error',
        confirmButtonText: 'Try Again'
      });
      console.error('Error:', error);
    } finally {
      setLoading(false);
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