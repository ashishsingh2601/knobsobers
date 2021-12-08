import React from 'react';
import {Button} from 'react-bootstrap';
import {Link} from 'react-router-dom';

import './LandingPage.css'
import {Grid} from '@material-ui/core';
import Typical from 'react-typical';
import { FaMapMarkerAlt, FaPhoneAlt, FaEnvelope, FaClock } from "react-icons/fa";


const LandingPage = () => {
    return (
        <>
        <div className="GridContainer" >
            <Grid container >
                
                <Grid item lg={6} md={6} xs={12} >
                    <div className="top-left-container">
                    <svg className="landingWave" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 300"><path fill="#0099ff" fill-opacity="1" d="M0,160L15,149.3C30,139,60,117,90,128C120,139,150,181,180,181.3C210,181,240,139,270,106.7C300,75,330,53,360,42.7C390,32,420,32,450,80C480,128,510,224,540,234.7C570,245,600,171,630,144C660,117,690,139,720,165.3C750,192,780,224,810,229.3C840,235,870,213,900,186.7C930,160,960,128,990,128C1020,128,1050,160,1080,154.7C1110,149,1140,107,1170,112C1200,117,1230,171,1260,160C1290,149,1320,75,1350,37.3C1380,0,1410,0,1425,0L1440,0L1440,0L1425,0C1410,0,1380,0,1350,0C1320,0,1290,0,1260,0C1230,0,1200,0,1170,0C1140,0,1110,0,1080,0C1050,0,1020,0,990,0C960,0,930,0,900,0C870,0,840,0,810,0C780,0,750,0,720,0C690,0,660,0,630,0C600,0,570,0,540,0C510,0,480,0,450,0C420,0,390,0,360,0C330,0,300,0,270,0C240,0,210,0,180,0C150,0,120,0,90,0C60,0,30,0,15,0L0,0Z"></path></svg>
                    </div>
                    
                </Grid>
                <Grid item lg={6} md={6} xs={12} >
                    <div className="top-right-container">
                        <h5 className="reg_heading"><Link to="/Signup">👉 New User? Register Here 👈</Link></h5>
                    </div>
                </Grid>
                
                
                <Grid item lg={8} md={8} xs={12} >
                <div className="land-front">
                <h1>Knobsobers</h1>
                <h6>
                    {" "}
                    <Typical
                        loop={Infinity}
                        steps={[
                            "be a Knowledge Absorber!💻",
                            1000,
                            "be a Real You 👨‍💻",
                            1000,
                        ]}
                        />
                    </h6> 
                <Link to="/login">
                <Button className="text-center">
                    Login
                </Button>
                </Link>
                </div>
                </Grid>
                <Grid item lg={4} md={4} xs={8}>
                    <div className="right-land-img">
                        <img src="./LandingImages/work_field3.PNG" />
                    </div>
                </Grid>
               
                <Grid item lg={12} sm={12} xs={12} spacing={2}>
                <div className="cardContainer">
                    <h1 className="aboutHeading">Explore us more</h1>
                        <p className="paraCard"><b>You will find us interesting </b></p>
                        <div className="row">
                        <Grid item lg={3} md={6} xs={12} >
                            <div className="card-01">
                                <img className="img1" src="./LandingImages/resourse_rating1.jpg" />
                                <div className="cardText" >
                                    <Link to="/SignUp"> <h5>Resource Rating</h5></Link>
                                <p>To have trusted resources for learning is a good start. Check out quality resources here and review them.</p>
                                </div>
                            </div>
                        </Grid>
                        <Grid item lg={3} md={6} xs={12} >
                        <div className="card-01">
                            <img className="img2" src="./LandingImages/exp1111.PNG" />
                            <div className="cardText" >
                            <Link to="/SignUp"> <h5>Experience recommendation</h5></Link>
                                <p>To learn from someone else's experiences is a best way to get success. We have gathered some and based on your need find experiences.</p>
                                </div></div>
                        </Grid>
                        <Grid item lg={3} md={6} xs={12} >
                        <div className="card-01">
                            <img className="img3" src="./LandingImages/refferal1.jpg" />
                            <div className="cardText" >
                            <Link to="/SignUp"> <h5>Seniors for referral</h5></Link>
                                <p>To open the door of opportunities for yourself, network to your seniors. We have few refferal providers, expend your knowledge pool now. </p>
                             </div>
                             </div>
                        </Grid>
                        <Grid item lg={3} md={6} xs={12} >
                        <div className="card-01">
                            <img className="img4" src="./LandingImages/1o1_mentor.jpg" />
                            <div className="cardText">
                            <Link to="/SignUp"> <h5>1:1 mentor recommendation</h5></Link>
                                <p>To reach the destination a correct guide is needed. Here, you can find your mentor according to your requirements.</p>
                                </div>
                                </div>
                        </Grid>
                        </div>
                        </div>
                </Grid>
                <Grid item lg={12} sm={12} xs={12} className="footerGridContainer">
                <h1>Get in touch</h1>
                    <div className="footer" >
                    
                    <div className="footerContainerDiv" >
                    <div className="row" id="row2" >
                        <Grid item lg={4} md={6} xs={12} >
                            <div className="card-02" id="card2">
                                
                                <div className="cardText2" >
                                    <Link to="/SignUp"> <h5>Contact Info</h5></Link>
                                    <p><FaMapMarkerAlt className="mapicon" />Center, Pro kunj, New Delhi, Delhi 110053</p>
                            <p><FaPhoneAlt /> +011 5990 5900</p>
                            <p><FaEnvelope />nikaggarwal2000@gmail.com</p>
                            <p><FaClock />Mon - Fri, 10:00AM - 5:30PM</p>
                                </div>
                            </div>
                        </Grid>
                        <Grid item lg={4} md={6} xs={12} >
                        <div className="card-02" id="card2">
                            
                            <div className="cardText2" ><h5>About Us</h5>
                            <p>we are Knobsobers with the aim to provide clear view to the graduates and learners for better networking,learning resources and experience sharing.</p>
                                </div></div>
                        </Grid>
                        <Grid item lg={4} md={6} xs={12}>
                        <div className="card-02" id="card2">
                           
                            <div className="cardText2" ><h5>Explore Us</h5>
                            <p>LinkedIn</p><p>Github</p><p>Twitter</p>
                             </div>
                             </div>
                        </Grid>
                       
                       
                        </div>
                        </div>
                            <svg className="footersvg" xmlns="http://www.w3.org/2000/svg" viewBox="200 121 1040 140"><path fill="#0099ff" fill-opacity="0.5" d="M0,32L30,80C60,128,120,224,180,240C240,256,300,192,360,165.3C420,139,480,149,540,170.7C600,192,660,224,720,208C780,192,840,128,900,122.7C960,117,1020,171,1080,202.7C1140,235,1200,245,1260,234.7C1320,224,1380,192,1410,176L1440,160L1440,320L1410,320C1380,320,1320,320,1260,320C1200,320,1140,320,1080,320C1020,320,960,320,900,320C840,320,780,320,720,320C660,320,600,320,540,320C480,320,420,320,360,320C300,320,240,320,180,320C120,320,60,320,30,320L0,320Z"></path></svg>
                        
                        </div>
                    
                </Grid>
            </Grid>
        </div>
        </>
    )
}

export default LandingPage
