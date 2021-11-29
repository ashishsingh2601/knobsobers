import React from 'react';
import {Route, Navigate, Outlet, Routes} from 'react-router-dom';
import {useAuth} from '../contexts/AuthContext';

const PrivateRoute = ({component: Component, ...rest}) => {

    const {currentUser} = useAuth();

    return (
        <>
        <Routes>
            <Route {...rest}
                    render={props => {
                        return currentUser ? <Outlet /> : <Navigate to="/login" />
                    }}
            >
            </Route>
        </Routes>       
        </>
    )
}

export default PrivateRoute;