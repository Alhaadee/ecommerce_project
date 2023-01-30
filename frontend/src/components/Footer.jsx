import React from "react";
import "../styles/Footer.css";

const Footer = () => {
  return (
    <footer>
    
          <div className="footer_column">
            <h3>Facebook</h3>
            <a href="https://en-gb.facebook.com/" className="footer_link">
              <img
                src="https://www.freepnglogos.com/uploads/facebook-icons/facebook-icon-transparent-background-3.png"
                alt="facebook logo"
                height="80px"
              ></img>
            </a>
          </div>
          <div className="footer_column">
            <h3>Instagram</h3>
            <a href="https://www.instagram.com/" className="footer_link">
              <img
                src="https://www.freepnglogos.com/uploads/instagram-icon-png/new-instagram-logo-png-6.png"
                alt="instagram logo"
                height="80px"
              ></img>
            </a>
          </div>
          <div className="footer_column">
            <h3>Twitter</h3>
            <a href="https://twitter.com/?lang=en" className="footer_link">
              <img
                src="https://www.freepnglogos.com/uploads/twitter-logo-png/twitter-logo-vector-png-clipart-1.png"
                alt="twitter logo"
                height="80px"
              ></img>
            </a>
          </div>
        
    </footer>
  );
};

export default Footer;
