/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { SuchAzMenuSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/such-az-menu-suchaz/such-az-menu-suchaz-delete-dialog.component';
import { SuchAzMenuSuchazService } from '../../../../../../main/webapp/app/entities/such-az-menu-suchaz/such-az-menu-suchaz.service';

describe('Component Tests', () => {

    describe('SuchAzMenuSuchaz Management Delete Component', () => {
        let comp: SuchAzMenuSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<SuchAzMenuSuchazDeleteDialogComponent>;
        let service: SuchAzMenuSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [SuchAzMenuSuchazDeleteDialogComponent],
                providers: [
                    SuchAzMenuSuchazService
                ]
            })
            .overrideTemplate(SuchAzMenuSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SuchAzMenuSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SuchAzMenuSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
