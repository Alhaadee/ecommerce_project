import React from 'react'
import { Link } from 'react-router-dom'
import "../styles/NavBar.css"

const NavBar = () => {
  return (
    <div className='header'>
    <div className='header-left'>
      <img src="" alt="Placeholder logo" />
      <div className='dropdown'>
        <button className='btn-link'>Shop</button>
        <div className='dropdown-menu'>
        <ul>
            <li><Link to="/">T-Shirts</Link></li>
            <li><Link to="/">Hoodies & Sweatshirts </Link></li>
            <li><Link to="/">Outerwear </Link></li>
            <li><Link to="/">Trousers </Link></li>
            <li><Link to="/">Shirts </Link></li>
        </ul>
        </div>
      </div>
      
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