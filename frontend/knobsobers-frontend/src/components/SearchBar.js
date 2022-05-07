import React from 'react';

const SearchBar = (props) => {
    return (
        <>
            <form className="search-box-container">
               <input 
                    className="search-box" 
                    placeholder="Search who you need..."
                    required 
                    name="search" 
                    // value={props.search}
                    onChange={props.filterUserCards} 
                    // onChange={e=>props.setSearch(e.target.value)}
                />
               {/* <button 
                    className="btn btn-transparent search-button" 
                    type="submit" 
                    onClick={props.handleAnimeSearch}
                    >Go
                </button> */}
            </form>
        </>
    )
}

export default SearchBar