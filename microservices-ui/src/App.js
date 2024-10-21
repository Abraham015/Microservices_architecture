import { Route, Routes } from 'react-router-dom';
import './App.css';
import Header from './components/header/Header';
import Login from './components/login/Login';
import Home from './components/home/Home';
import Footer from './components/footer/Footer';
import Register from "./components/register/Register";

function App() {
  return (
    <div className='d-flex flex-column gap-1'>
      <Header />
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/login' element={<Login />} />
          <Route path="/register" element={<Register/>}/>
      </Routes>
      <Footer/>
    </div>
  );
}

export default App;