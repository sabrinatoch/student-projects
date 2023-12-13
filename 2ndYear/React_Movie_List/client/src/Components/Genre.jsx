import React from "react";

export default function Genre({ genre }) {
  let list = "Genre: ";
  genre.map((gen, idx) => {
    if (idx != genre.length - 1) list += `${gen}, `;
    else list += `${gen}`;
  });

  return <p className="genre">{list}</p>;
} // Genre()
