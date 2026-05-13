class ItemListForm {
  final int id;
  final String name;
  final String description;
  final String owner;

  ItemListForm({
    required this.id,
    required this.name,
    required this.description,
    required this.owner
  });

  // Convert JSON Map to ItemListForm object
  factory ItemListForm.fromJson(Map<String, dynamic> json) {
    return ItemListForm(
      id: json['id'] as int,
      name: json['name'] as String,
      description: json['description'] as String,
      owner: json['owner'] as String,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'description': description,
      'owner': owner,
    };
  }
}