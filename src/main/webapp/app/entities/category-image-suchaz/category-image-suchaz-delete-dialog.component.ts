import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CategoryImageSuchaz } from './category-image-suchaz.model';
import { CategoryImageSuchazPopupService } from './category-image-suchaz-popup.service';
import { CategoryImageSuchazService } from './category-image-suchaz.service';

@Component({
    selector: 'jhi-category-image-suchaz-delete-dialog',
    templateUrl: './category-image-suchaz-delete-dialog.component.html'
})
export class CategoryImageSuchazDeleteDialogComponent {

    categoryImage: CategoryImageSuchaz;

    constructor(
        private categoryImageService: CategoryImageSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.categoryImageService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'categoryImageListModification',
                content: 'Deleted an categoryImage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-category-image-suchaz-delete-popup',
    template: ''
})
export class CategoryImageSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private categoryImagePopupService: CategoryImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.categoryImagePopupService
                .open(CategoryImageSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
