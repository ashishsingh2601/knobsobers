import React from 'react';
import MainNavbar from './Navbar/MainNavbar';
import {Button} from 'antd';
import AddExp from './AddExp';
import SearchBar from './SearchBar';
import ExperienceCard from './ExperienceCard';

const Experiences = () => {


    // const [addUserExpCard, setAddUserExpCard]  = useState({
    //     name: "",
    //     domain: "",
    //     companyName: "",
    //     desc: "",
    // });

    return (
        <div>
            <MainNavbar />
            <div className="exp-wrapper">
                <div className="exp-wrapper-left">
                    Filter By
                </div>
                <div className="exp-wrapper-right-container">
                    <div className="exp-wrapper-right-head">
                        <SearchBar />
                        <AddExp />
                    </div>
                    <div className="exp-wrapper-right-content">
                        <ExperienceCard />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Experiences
