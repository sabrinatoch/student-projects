import React from "react";

export default function Actor({ actors }) {
  let list = "Actors: ";
  actors.map((actor, idx) => {
    if (idx != actors.length - 1) list += `${actor}, `;
    else list += `${actor}`;
  });

  return <p className="actors">{list}</p>;
} // Actor()
