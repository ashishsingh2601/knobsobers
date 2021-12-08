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
import ImageUpload from './ImageUpload';

function App() {
  return (
     <>
     <Container>
        <div>
          <Router>
            <AuthProvider>
              <Routes>
                <Route exact path="/" element={<LandingPage />} />
                {/* <Route exact path="/dashboard" element={<PrivateRoute />}> */}
                  <Route path="/dashboard" element={<Dashboard />} />
                {/* </Route> */}
                <Route path="/signup" element={<Signup />} />
                <Route path="/image-upload" element={<ImageUpload />} />
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
