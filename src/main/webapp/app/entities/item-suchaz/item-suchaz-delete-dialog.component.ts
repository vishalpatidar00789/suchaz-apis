import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ItemSuchaz } from './item-suchaz.model';
import { ItemSuchazPopupService } from './item-suchaz-popup.service';
import { ItemSuchazService } from './item-suchaz.service';

@Component({
    selector: 'jhi-item-suchaz-delete-dialog',
    templateUrl: './item-suchaz-delete-dialog.component.html'
})
export class ItemSuchazDeleteDialogComponent {

    item: ItemSuchaz;

    constructor(
        private itemService: ItemSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.itemService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'itemListModification',
                content: 'Deleted an item'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-item-suchaz-delete-popup',
    template: ''
})
export class ItemSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private itemPopupService: ItemSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.itemPopupService
                .open(ItemSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
