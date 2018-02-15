import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { StoreSuchaz } from './store-suchaz.model';
import { StoreSuchazPopupService } from './store-suchaz-popup.service';
import { StoreSuchazService } from './store-suchaz.service';

@Component({
    selector: 'jhi-store-suchaz-delete-dialog',
    templateUrl: './store-suchaz-delete-dialog.component.html'
})
export class StoreSuchazDeleteDialogComponent {

    store: StoreSuchaz;

    constructor(
        private storeService: StoreSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.storeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'storeListModification',
                content: 'Deleted an store'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-store-suchaz-delete-popup',
    template: ''
})
export class StoreSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private storePopupService: StoreSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.storePopupService
                .open(StoreSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
