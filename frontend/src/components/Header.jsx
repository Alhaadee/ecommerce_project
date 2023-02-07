import React, { useEffect, useState, useRef } from 'react'
import { Link } from 'react-router-dom'
import "../styles/NavBar.css"

const Header = () => {
  
  const [open, setOpen] = useState(false);

  let menuRef = useRef();

  const toggleDropdown = () => {
    setOpen(!open)
  }

  useEffect(()=>{
    let handler = (e)=>{
      if(!menuRef.current.contains(e.target)){
        setOpen(false);
      }
    };

    document.addEventListener("mousedown", handler)
  })

  return (
    <header >
    <div className='header-left'>
      <img src="" alt="Placeholder logo" />
      <div className='dropdown' ref={menuRef}>
        <button className='btn-link' onClick={toggleDropdown}>Shop</button>
        <div className={`dropdown-menu ${open? 'active' : 'inactive'}`}>
          <ul>
              <li><Link to="/headphones" onClick={toggleDropdown}>Headphones</Link></li>
              <li><Link to="/" onClick={toggleDropdown}>TVs & Monitors</Link></li>
              <li><Link to="/" onClick={toggleDropdown}>Accesories</Link></li>
              <li><Link to="/" onClick={toggleDropdown}>Gaming</Link></li>
              <li><Link to="/" onClick={toggleDropdown}>PC parts</Link></li>
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