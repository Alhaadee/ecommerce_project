import React from 'react'
import { Link } from 'react-router-dom'
import "../styles/NavBar.css"

const NavBar = () => {
  return (
    <div className='header'>
    <div className='header-left'>
      <img src="" alt="Placeholder logo" />
      <span><Link to="/">Shop</Link></span>
    </div>
   
    <span className='search-bar'> <img src="" alt="magnifying glass" /> <input type="text" placeholder='Search' /></span>
    <nav>
        <ul>
            <li><Link to="/">Login</Link></li>
            <li><Link to="/">Shopping Basket</Link></li>
        </ul>
    </nav>
    
     
    </div>
  )
}

export default NavBar