// Wait for DOM to load
document.addEventListener('DOMContentLoaded', function() {
  // Kiểm tra IntersectionObserver có được hỗ trợ không
  if ('IntersectionObserver' in window) {
    initAnimations();
  } else {
    // Fallback cho trình duyệt không hỗ trợ IntersectionObserver
    const animatedElements = document.querySelectorAll('.hero-section h1, .hero-title, .section-heading, .feature-title, .testimonial-heading, .trial-heading, .btn-primary-blue, .btn-white, .feature-item, .testimonial-card, .icon-box');
    animatedElements.forEach(element => {
      element.style.opacity = '1';
      element.style.transform = 'translateY(0)';
    });
  }
  
  createWaves();
  initMobileMenu();
  initScrollSpy();
  initButtonEffects();
  initSmoothScroll();
});

// Animate elements when they enter the viewport
function initAnimations() {
  const animatedElements = document.querySelectorAll('.hero-section h1, .hero-title, .section-heading, .feature-title, .testimonial-heading, .trial-heading, .btn-primary-blue, .btn-white, .feature-item, .testimonial-card, .icon-box');
  
  // Set initial styles
  animatedElements.forEach(element => {
    element.style.opacity = '0';
    element.style.transform = 'translateY(20px)';
    element.style.transition = 'opacity 0.6s ease, transform 0.6s ease';
  });
  
  // Create observer
  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        setTimeout(() => {
          entry.target.style.opacity = '1';
          entry.target.style.transform = 'translateY(0)';
        }, 50);
        observer.unobserve(entry.target);
      }
    });
  }, {
    root: null,
    threshold: 0.1,
    rootMargin: '0px 0px -20px 0px'
  });
  
  // Observe each element
  animatedElements.forEach(element => {
    observer.observe(element);
  });
  
  // Animate hero elements immediately
  const heroElements = document.querySelectorAll('.hero-section h1, .hero-section p, .hero-section .btn');
  setTimeout(() => {
    heroElements.forEach(element => {
      element.style.opacity = '1';
      element.style.transform = 'translateY(0)';
    });
  }, 100);
}

// Create decorative SVG waves
function createWaves() {
  try {
    // Paths for top and bottom waves
    const wavePaths = {
      top: "M0,40 C150,100 350,0 500,40 C650,80 850,0 1000,40 L1000,0 L0,0 Z",
      bottom: "M0,40 C150,0 350,80 500,40 C650,0 850,80 1000,40 L1000,100 L0,100 Z"
    };
    
    // Handle wave header (dark wave at bottom of hero section)
    const heroWave = document.querySelector('.wave-header');
    if (heroWave) {
      renderWave(heroWave, wavePaths.bottom, '#0f0f0f', 1);
    }
    
    // Handle other wave sections
    const waveTopElements = document.querySelectorAll('.wave-top');
    waveTopElements.forEach(element => {
      renderWave(element, wavePaths.top, '#f9fafb', 1);
    });
    
    const waveBottomElements = document.querySelectorAll('.wave-bottom');
    waveBottomElements.forEach(element => {
      renderWave(element, wavePaths.bottom, '#0f0f0f', 1);
    });
    
    // Add decorative circles to dark sections
    const darkSections = document.querySelectorAll('.dark-section, .testimonial-section');
    darkSections.forEach(section => {
      addDecorativeElements(section);
    });
  } catch (error) {
    console.log('Error creating waves:', error);
  }
}

// Render an SVG wave
function renderWave(container, path, fill, opacity) {
  try {
    const svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    svg.setAttribute("xmlns", "http://www.w3.org/2000/svg");
    svg.setAttribute("viewBox", "0 0 1000 100");
    svg.setAttribute("preserveAspectRatio", "none");
    svg.style.width = "100%";
    svg.style.height = "100%";
    
    const pathElement = document.createElementNS("http://www.w3.org/2000/svg", "path");
    pathElement.setAttribute("d", path);
    pathElement.setAttribute("fill", fill);
    pathElement.setAttribute("fill-opacity", opacity);
    
    svg.appendChild(pathElement);
    // Clear existing content before appending
    container.innerHTML = '';
    container.appendChild(svg);
  } catch (error) {
    console.log('Error rendering wave:', error);
  }
}

// Add decorative elements to a section
function addDecorativeElements(section) {
  try {
    // Remove existing decorative elements first
    const existingElements = section.querySelectorAll('.decorative-curve');
    existingElements.forEach(el => el.remove());
    
    // Add first circle
    const circle1 = document.createElement('div');
    circle1.className = 'decorative-curve';
    circle1.style.width = '150px';
    circle1.style.height = '150px';
    circle1.style.top = '20%';
    circle1.style.left = '5%';
    section.appendChild(circle1);
    
    // Add second circle
    const circle2 = document.createElement('div');
    circle2.className = 'decorative-curve';
    circle2.style.width = '120px';
    circle2.style.height = '120px';
    circle2.style.bottom = '15%';
    circle2.style.right = '10%';
    section.appendChild(circle2);
    
    // Ensure the section has the right style properties
    section.style.position = 'relative';
    section.style.overflow = 'hidden';
  } catch (error) {
    console.log('Error adding decorative elements:', error);
  }
}

// Initialize mobile menu
function initMobileMenu() {
  const mobileToggle = document.querySelector('.mobile-menu-toggle');
  
  if (!mobileToggle) return;
  
  mobileToggle.addEventListener('click', function() {
    let mobileMenu = document.querySelector('.mobile-menu-overlay');
    
    if (!mobileMenu) {
      createMobileMenu();
    } else {
      mobileMenu.style.opacity = '0';
      setTimeout(() => {
        if (mobileMenu.parentNode) {
          mobileMenu.parentNode.removeChild(mobileMenu);
        }
      }, 300);
    }
  });
}

// Create mobile menu overlay
function createMobileMenu() {
  try {
    // Remove existing menu if any
    const existingMenu = document.querySelector('.mobile-menu-overlay');
    if (existingMenu) {
      document.body.removeChild(existingMenu);
    }
    
    const mobileMenu = document.createElement('div');
    mobileMenu.className = 'mobile-menu-overlay';
    
    // Create close button
    const closeBtn = document.createElement('button');
    closeBtn.innerHTML = '<i class="fas fa-times"></i>';
    closeBtn.style.position = 'absolute';
    closeBtn.style.top = '20px';
    closeBtn.style.right = '20px';
    closeBtn.style.backgroundColor = 'transparent';
    closeBtn.style.border = 'none';
    closeBtn.style.color = 'white';
    closeBtn.style.fontSize = '24px';
    closeBtn.style.cursor = 'pointer';
    
    closeBtn.addEventListener('click', function() {
      mobileMenu.style.opacity = '0';
      setTimeout(() => {
        if (document.body.contains(mobileMenu)) {
          document.body.removeChild(mobileMenu);
        }
      }, 300);
    });
    
    // Clone navigation links
    const navLinks = document.querySelectorAll('.navbar-nav .nav-link');
    const navContainer = document.createElement('div');
    navContainer.style.textAlign = 'center';
    
    navLinks.forEach(link => {
      const newLink = link.cloneNode(true);
      newLink.style.display = 'block';
      newLink.style.fontSize = '20px';
      newLink.style.margin = '15px 0';
      newLink.style.color = 'white';
      newLink.style.textDecoration = 'none';
      
      newLink.addEventListener('click', function() {
        mobileMenu.style.opacity = '0';
        setTimeout(() => {
          if (document.body.contains(mobileMenu)) {
            document.body.removeChild(mobileMenu);
          }
        }, 300);
      });
      
      navContainer.appendChild(newLink);
    });
    
    // Add button from header
    const headerButton = document.querySelector('.navbar .btn-primary-blue');
    if (headerButton) {
      const menuButton = headerButton.cloneNode(true);
      menuButton.style.margin = '30px auto 0';
      menuButton.style.display = 'inline-block';
      navContainer.appendChild(menuButton);
    }
    
    // Append elements to menu
    mobileMenu.appendChild(closeBtn);
    mobileMenu.appendChild(navContainer);
    document.body.appendChild(mobileMenu);
    
    // Trigger animation
    setTimeout(() => {
      mobileMenu.style.opacity = '1';
    }, 10);
  } catch (error) {
    console.log('Error creating mobile menu:', error);
  }
}

// Initialize scrollspy
function initScrollSpy() {
  const sections = document.querySelectorAll('section[id]');
  const navLinks = document.querySelectorAll('.navbar-nav .nav-link');
  
  if (!sections.length || !navLinks.length) return;
  
  window.addEventListener('scroll', function() {
    let current = '';
    const scrollY = window.pageYOffset;
    
    // Find current section
    sections.forEach(section => {
      const sectionTop = section.offsetTop - 100;
      const sectionHeight = section.offsetHeight;
      
      if (scrollY >= sectionTop && scrollY < sectionTop + sectionHeight) {
        current = section.getAttribute('id');
      }
    });
    
    // Update active link
    navLinks.forEach(link => {
      link.classList.remove('active');
      const href = link.getAttribute('href');
      if (href && href === '#' + current) {
        link.classList.add('active');
      }
    });
  });
  
  // Trigger scroll event once to set initial active state
  window.dispatchEvent(new Event('scroll'));
}

// Initialize button hover effects
function initButtonEffects() {
  const buttons = document.querySelectorAll('.btn-primary-blue, .btn-white');
  
  buttons.forEach(button => {
    button.addEventListener('mouseenter', function() {
      this.style.transform = 'translateY(-2px)';
      this.style.boxShadow = this.classList.contains('btn-primary-blue') 
        ? '0 8px 15px rgba(59, 130, 246, 0.3)' 
        : '0 8px 15px rgba(255, 255, 255, 0.3)';
    });
    
    button.addEventListener('mouseleave', function() {
      this.style.transform = '';
      this.style.boxShadow = '';
    });
  });
}

// Initialize smooth scrolling
function initSmoothScroll() {
  document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
      if (this.getAttribute('href') === '#') return;
      
      e.preventDefault();
      const targetId = this.getAttribute('href');
      const targetElement = document.querySelector(targetId);
      
      if (!targetElement) return;
      
      const headerOffset = 70;
      const elementPosition = targetElement.getBoundingClientRect().top;
      const offsetPosition = elementPosition + window.pageYOffset - headerOffset;
      
      window.scrollTo({
        top: offsetPosition,
        behavior: 'smooth'
      });
    });
  });
} 