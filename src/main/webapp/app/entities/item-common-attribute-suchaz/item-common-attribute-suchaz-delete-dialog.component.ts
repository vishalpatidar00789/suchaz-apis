import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ItemCommonAttributeSuchaz } from './item-common-attribute-suchaz.model';
import { ItemCommonAttributeSuchazPopupService } from './item-common-attribute-suchaz-popup.service';
import { ItemCommonAttributeSuchazService } from './item-common-attribute-suchaz.service';

@Component({
    selector: 'jhi-item-common-attribute-suchaz-delete-dialog',
    templateUrl: './item-common-attribute-suchaz-delete-dialog.component.html'
})
export class ItemCommonAttributeSuchazDeleteDialogComponent {

    itemCommonAttribute: ItemCommonAttributeSuchaz;

    constructor(
        private itemCommonAttributeService: ItemCommonAttributeSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.itemCommonAttributeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'itemCommonAttributeListModification',
                content: 'Deleted an itemCommonAttribute'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-item-common-attribute-suchaz-delete-popup',
    template: ''
})
export class ItemCommonAttributeSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private itemCommonAttributePopupService: ItemCommonAttributeSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.itemCommonAttributePopupService
                .open(ItemCommonAttributeSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
