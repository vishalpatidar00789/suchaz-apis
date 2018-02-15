import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CategorySuchaz } from './category-suchaz.model';
import { CategorySuchazPopupService } from './category-suchaz-popup.service';
import { CategorySuchazService } from './category-suchaz.service';

@Component({
    selector: 'jhi-category-suchaz-delete-dialog',
    templateUrl: './category-suchaz-delete-dialog.component.html'
})
export class CategorySuchazDeleteDialogComponent {

    category: CategorySuchaz;

    constructor(
        private categoryService: CategorySuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.categoryService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'categoryListModification',
                content: 'Deleted an category'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-category-suchaz-delete-popup',
    template: ''
})
export class CategorySuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private categoryPopupService: CategorySuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.categoryPopupService
                .open(CategorySuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
