import React, {useState} from 'react';
import {Button, Card, Alert} from 'react-bootstrap';
import {useAuth} from "../../contexts/AuthContext";
import MainNavbar from '../Navbar/MainNavbar';
import {BrowserRouter as Router, Route, Routes, Link, useNavigate} from 'react-router-dom';
import Experiences from '../Experiences';

const Dashboard = () => {
    
    // const [error, setError] = useState('');
    // const { logout, currentUser } = useAuth();
    // const navigate = useNavigate();

    // const handleLogout = async () => {
    //     setError('');
    //     try{
    //         await logout();
    //         navigate("/login");
    //     }catch{
    //         setError('Failed to Log-out');
    //     }
    // }

    return (
        <>
            {/* <Router>
                <Routes>
                    <Route path="/dashboard/experiences" element={<Experiences />} />
                </Routes>
            </Router> */}
                <MainNavbar />
        </>  
            
    );
}

export default Dashboard
