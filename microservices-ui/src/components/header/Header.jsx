import React from "react";
import {Link, useLocation} from "react-router-dom";

const Header = () => {

  const location=useLocation()

  return (
    <nav className="navbar navbar-expand-lg bg-body-tertiary">
      <div className="container-fluid">
        <a className="navbar-brand" href="javascript:void(0)">
          E-Commerce
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="#navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <Link to="/" className="nav-link">
                <i className="fas fa-home-alt"></i>&nbsp; Home
              </Link>
            </li>
            <li className="nav-item">
              <a
                className="nav-link"
                aria-current="page"
              >
                <i className="fas fa-heart"></i>&nbsp; My orders
              </a>
            </li>
            <li className="nav-item">
              <a
                className="nav-link"
                aria-current="page"
              >
                <i className="fas fa-book-reader"></i>&nbsp; My payments
              </a>
            </li>
          </ul>
          {
            location.pathname !== "/login"  && location.pathname !== "/register" &&(
                  <Link to="/login" className="btn btn-info text-bg-primary">
                    <i className="fa fa-sign-in"></i> Login
                  </Link>
              )
          }
        </div>
      </div>
    </nav>
  );
};

export default Header;
