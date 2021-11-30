import './App.css';
import React from 'react';
import Signup from './Signup';
import {Container} from 'react-bootstrap';
import {AuthProvider} from '../contexts/AuthContext';
import {BrowserRouter as Router, Route, Routes, Link} from 'react-router-dom';
import Dashboard from './Dashboard/Dashboard';
import LandingPage from './LandingPage/LandingPage';
import Login from './Login';
import ErrorPage from './ErrorPage';
import PrivateRoute from './PrivateRoute';
import ForgotPassword from './ForgotPassword';

function App() {
  return (
     <>
     <Container className="d-flex align-items-center justify-content-center" style={{minHeight: '100vh'}}>
        <div className="w-100" style={{maxWidth: '450px'}}>
          <Router>
            <AuthProvider>
              <Routes>
                <Route exact path="/" element={<LandingPage />} />
                {/* <Route exact path="/dashboard" element={<PrivateRoute />}> */}
                  <Route path="/dashboard" element={<Dashboard />} />
                {/* </Route> */}
                <Route path="/signup" element={<Signup />} />
                <Route path="/login" element={<Login />} />
                <Route path="/forgot-password" element={<ForgotPassword />} />
                <Route path="*" element={<ErrorPage />} />
              </Routes>
            </AuthProvider>
          </Router>
        </div>
      </Container>
    </>
  );
}

export default App;
