import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SuchAzMenuSuchaz } from './such-az-menu-suchaz.model';
import { SuchAzMenuSuchazPopupService } from './such-az-menu-suchaz-popup.service';
import { SuchAzMenuSuchazService } from './such-az-menu-suchaz.service';

@Component({
    selector: 'jhi-such-az-menu-suchaz-delete-dialog',
    templateUrl: './such-az-menu-suchaz-delete-dialog.component.html'
})
export class SuchAzMenuSuchazDeleteDialogComponent {

    suchAzMenu: SuchAzMenuSuchaz;

    constructor(
        private suchAzMenuService: SuchAzMenuSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.suchAzMenuService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'suchAzMenuListModification',
                content: 'Deleted an suchAzMenu'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-such-az-menu-suchaz-delete-popup',
    template: ''
})
export class SuchAzMenuSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private suchAzMenuPopupService: SuchAzMenuSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.suchAzMenuPopupService
                .open(SuchAzMenuSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
