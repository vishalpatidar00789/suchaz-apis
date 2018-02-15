import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ItemAttributeTypeSuchaz } from './item-attribute-type-suchaz.model';
import { ItemAttributeTypeSuchazPopupService } from './item-attribute-type-suchaz-popup.service';
import { ItemAttributeTypeSuchazService } from './item-attribute-type-suchaz.service';

@Component({
    selector: 'jhi-item-attribute-type-suchaz-delete-dialog',
    templateUrl: './item-attribute-type-suchaz-delete-dialog.component.html'
})
export class ItemAttributeTypeSuchazDeleteDialogComponent {

    itemAttributeType: ItemAttributeTypeSuchaz;

    constructor(
        private itemAttributeTypeService: ItemAttributeTypeSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.itemAttributeTypeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'itemAttributeTypeListModification',
                content: 'Deleted an itemAttributeType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-item-attribute-type-suchaz-delete-popup',
    template: ''
})
export class ItemAttributeTypeSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private itemAttributeTypePopupService: ItemAttributeTypeSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.itemAttributeTypePopupService
                .open(ItemAttributeTypeSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
