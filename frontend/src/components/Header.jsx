import React from 'react'
import { Link } from 'react-router-dom'
import "../styles/NavBar.css"

const Header = () => {


  return (
    <header >
    <div className='header-left'>
      <img src="" alt="Placeholder logo" />
      <div className='dropdown'>
        <button className='btn-link'>Shop</button>
        <div className='dropdown-menu'>
        <ul>
            <li><Link to="/">Headphones</Link></li>
            <li><Link to="/">TVs & Monitors</Link></li>
            <li><Link to="/">Accesories</Link></li>
            <li><Link to="/">Gaming</Link></li>
            <li><Link to="/">PC parts</Link></li>
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
    
     
    </header>
  )
}

export default Header